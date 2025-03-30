package cn.kutori.method;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import lombok.extern.slf4j.Slf4j;

/**
 * 模拟鼠标点击
 */
@Slf4j
public class SimulateClick {

    /**
     * 鼠标事件
     */
    // 表示物理动作，直接触发鼠标按下行为
    // 使用 mouse_event() 或 SendInput() 触发
    // 模拟真实鼠标操作，影响全局（任何窗口、桌面等）
    public static final int MOUSEEVENTF_MOVE = 0x0001;        // 移动鼠标
    public static final int MOUSEEVENTF_LEFTDOWN = 0x0002;    // 按下鼠标左键
    public static final int MOUSEEVENTF_LEFTUP = 0x0004;      // 释放鼠标左键
    public static final int MOUSEEVENTF_RIGHTDOWN = 0x0008;   // 按下鼠标右键
    public static final int MOUSEEVENTF_RIGHTUP = 0x0010;     // 释放鼠标右键
    public static final int MOUSEEVENTF_MIDDLEDOWN = 0x0020;  // 按下鼠标中键
    public static final int MOUSEEVENTF_MIDDLEUP = 0x0040;    // 释放鼠标中键
    public static final int MOUSEEVENTF_ABSOLUTE = 0x8000;    // 绝对位置

    // 表示窗口消息，通知窗口有鼠标左键按下的事件发生
    // 使用 PostMessage() 或 SendMessage() 发送
    // 仅影响指定窗口，不会触发系统全局响应。
    public static final int WM_LBUTTONDOWN = 0x0201; // 鼠标左键按下
    public static final int WM_MOUSEMOVE = 0x0200;   // 鼠标移动
    public static final int WM_LBUTTONUP = 0x0202;   // 鼠标左键抬起
    public static final int MK_LBUTTON = 0x0001; // 定义鼠标左键按下标志

    /**
     * 模拟鼠标点击
     * @param hwnd 窗口句柄
     * @param x    x坐标
     * @param y    y坐标
     */
    public void sendMouseClick(WinDef.HWND hwnd, int x, int y) {
        // 将窗口置于前台
        User32.INSTANCE.SetWindowPos(hwnd, null,
                0, 0, 0, 0, User32.SWP_NOMOVE | User32.SWP_NOSIZE); // 窗口置顶
        User32.INSTANCE.ShowWindow(hwnd, User32.SW_RESTORE); // 确保窗口被恢复
        // 使用 SendInput 模拟鼠标点击
        sendClick(x, y);
    }

    /**
     * 模拟页面滑动(无需置顶的页面滑动)
     * @param hwnd 窗口句柄
     * @param startX 起始 X 坐标
     * @param startY 起始 Y 坐标
     * @param endX   结束 X 坐标
     * @param endY   结束 Y 坐标
     */
    public static void swipeLeft(WinDef.HWND hwnd, int startX, int startY, int endX, int endY) {
        // 模拟鼠标按下（这里 wParam 保持为 0 也可以）
        User32.INSTANCE.PostMessage(hwnd, WM_LBUTTONDOWN, new WinDef.WPARAM(0),
                new WinDef.LPARAM(((long) startY << 16) | startX));
        // 模拟鼠标拖动
        int stepCount = 10; // 滑动步数
        for (int i = 1; i <= stepCount; i++) {
            int currentX = startX + (endX - startX) * i / stepCount;
            int currentY = startY + (endY - startY) * i / stepCount;
            // 在鼠标移动时传入 MK_LBUTTON 标志，表示左键仍然按下
            User32.INSTANCE.PostMessage(hwnd, WM_MOUSEMOVE, new WinDef.WPARAM(MK_LBUTTON),
                    new WinDef.LPARAM(((long) currentY << 16) | currentX));
            try {
                Thread.sleep(50); // 每次延迟 50ms
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // 模拟鼠标抬起
        User32.INSTANCE.PostMessage(hwnd, WM_LBUTTONUP, new WinDef.WPARAM(0),
                new WinDef.LPARAM(((long) endY << 16) | endX));
        System.out.println("页面滑动完成！");
    }

    /**
     * 模拟鼠标点击（需要置顶的页面滑动）
     * @param x 点击的屏幕 X 坐标
     * @param y 点击的屏幕 Y 坐标
     */
    public static void sendClick(int x, int y) {

        WinUser.INPUT input = new WinUser.INPUT();
        input.type = new WinDef.DWORD(WinUser.INPUT.INPUT_MOUSE);

        WinUser.MOUSEINPUT mouseInput = new WinUser.MOUSEINPUT();

        // **发送鼠标点击事件**
        // public class INPUT extends Structure {
        //    public DWORD type;     // 指定输入类型（鼠标、键盘、硬件）
        //    public UNION input;    // 实际的输入数据（鼠标、键盘或硬件）
        //
        //    public static class UNION extends Union {
        //        public MOUSEINPUT mi;  // 鼠标输入
        //        public KEYBDINPUT ki;  // 键盘输入
        //        public HARDWAREINPUT hi; // 硬件输入
        //    }
        //}
        input.input.setType("mi");
        input.input.mi = mouseInput;

        // 获取屏幕分辨率
        int screenWidth = User32.INSTANCE.GetSystemMetrics(
                WinUser.SM_CXSCREEN); // 获取屏幕宽度
        int screenHeight = User32.INSTANCE.GetSystemMetrics(
                WinUser.SM_CYSCREEN); // 获取屏幕高度

        // 模拟鼠标移动到指定坐标
        // **发送鼠标移动事件**
        mouseInput.dx = new WinDef.LONG(
                x * 65536L / screenWidth); // 转换为绝对坐标
        mouseInput.dy = new WinDef.LONG(
                y * 65536L / screenHeight);
        mouseInput.dwFlags = new WinDef.DWORD(MOUSEEVENTF_MOVE | MOUSEEVENTF_ABSOLUTE);
        User32.INSTANCE.SendInput(new WinDef.DWORD(1), new WinUser.INPUT[]{input}, input.size());

        // 按下鼠标左键
        mouseInput.dwFlags = new WinDef.DWORD(MOUSEEVENTF_LEFTDOWN);
        User32.INSTANCE.SendInput(new WinDef.DWORD(1), new WinUser.INPUT[]{input}, input.size());

        // 释放鼠标左键
        mouseInput.dwFlags = new WinDef.DWORD(MOUSEEVENTF_LEFTUP);
        User32.INSTANCE.SendInput(new WinDef.DWORD(1), new WinUser.INPUT[]{input}, input.size());
    }

}

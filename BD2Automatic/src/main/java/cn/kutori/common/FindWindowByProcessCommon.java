package cn.kutori.common;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.*;
import com.sun.jna.ptr.IntByReference;

/**
 * 获取窗口和句柄工具
 */
public class FindWindowByProcessCommon {

    /**
     * 传入想要获取的名称
     * @param processName 传入想要获取的名称
     * @return 句柄
     */
    public static WinDef.HWND getWindowByProcessName(String processName) {
        WinNT.HANDLE snapshot = Kernel32.INSTANCE.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPPROCESS, new WinDef.DWORD(0));
        Tlhelp32.PROCESSENTRY32 processEntry = new Tlhelp32.PROCESSENTRY32();
        WinDef.HWND hwnd = null;

        try {
            if (Kernel32.INSTANCE.Process32First(snapshot, processEntry)) {
                do {
                    String exeName = Native.toString(processEntry.szExeFile);
                    if (exeName.equalsIgnoreCase(processName)) {
                        int pid = processEntry.th32ProcessID.intValue();
                        hwnd = findWindowByPID(pid);
                        break;
                    }
                } while (Kernel32.INSTANCE.Process32Next(snapshot, processEntry));
            }
        } finally {
            Kernel32.INSTANCE.CloseHandle(snapshot);
        }
        return hwnd;
    }

    /**
     * 获取句柄
     * @param pid 进程号
     * @return 句柄
     */
    public static WinDef.HWND findWindowByPID(int pid) {
        final WinDef.HWND[] result = {null};
        User32.INSTANCE.EnumWindows((hwnd, pointer) -> {
            IntByReference pidRef = new IntByReference();
            User32.INSTANCE.GetWindowThreadProcessId(hwnd, pidRef);
            if (pidRef.getValue() == pid) {
                result[0] = hwnd;
                return false; // 找到目标窗口后停止枚举
            }
            return true;
        }, null);
        if (result[0] != null) {
            System.out.println("找到窗口句柄:" + Pointer.nativeValue(result[0].getPointer()));
        } else {
            System.out.println("未找到窗口句柄:" + pid);
        }
        return result[0];
    }
}
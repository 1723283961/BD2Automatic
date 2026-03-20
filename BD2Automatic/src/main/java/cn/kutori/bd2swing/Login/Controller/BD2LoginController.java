package cn.kutori.bd2swing.Login.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class BD2LoginController {

    @FXML private Label titleLabel;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmField;
    @FXML private Button actionBtn;
    @FXML private Label msgLabel;
    @FXML private Hyperlink switchLink;

    private boolean isLogin = true;

    @FXML
    /**
    * 切换登录/注册模式的处理方法
    * 通过点击链接按钮触发，实现登录和注册表单之间的切换
    */
    private void switchMode() {
        // 切换登录/注册模式的状态标志
        isLogin = !isLogin;
        // 根据当前模式设置标题文本
        titleLabel.setText(isLogin ? "Sign In" : "Register");
        // 根据当前模式设置操作按钮文本
        actionBtn.setText(isLogin ? "Login" : "Register");
        // 根据当前模式设置切换链接文本
        switchLink.setText(
                isLogin ? "No account? Register" : "Already have an account? Login"
        );
        // 根据当前模式控制确认密码字段的可见性
        confirmField.setVisible(!isLogin);
        // 根据当前模式控制确认密码字段的管理性（布局计算）
        confirmField.setManaged(!isLogin);
        // 清空消息标签文本
        msgLabel.setText("");
    }

    @FXML
    /**
     * 处理登录/注册按钮点击事件的动作方法
     * @param event ActionEvent事件对象，用于获取事件源相关信息
     * @throws IOException 可能在界面跳转时抛出的IO异常
     */
    private void handleAction(ActionEvent event) throws IOException {
        // 获取用户输入的用户名
        String user = usernameField.getText();
        // 获取用户输入的密码
        String pass = passwordField.getText();

        // 检查用户名或密码是否为空
        if (user.isEmpty() || pass.isEmpty()) {
            // 设置错误提示信息
            msgLabel.setText("请输入用户名和密码");
            return;  // 终止方法执行
        }

        // 判断当前是登录模式还是注册模式
        if (isLogin) {
            // 👉 模拟登录逻辑
            // 验证用户名和密码是否为预设的管理员账号
            if ("admin".equals(user) && "123456".equals(pass)) {
                // 登录成功，跳转到主界面
                goMain(event);
            } else {
                // 登录失败，显示错误信息
                msgLabel.setText("用户名或密码错误");
            }
        } else {
            // 👉 注册逻辑
            // 检查两次输入的密码是否一致
            if (!pass.equals(confirmField.getText())) {
                // 密码不一致，显示错误信息
                msgLabel.setText("两次密码不一致");
                return;  // 终止方法执行
            }
            // 注册成功，显示成功信息并切换到登录模式
            msgLabel.setText("注册成功，请登录");
            switchMode();  // 切换登录/注册模式
        }
    }

    /**
    * 导航到主界面的方法
    * @param event 事件对象，用于获取触发事件的源组件
    * @throws IOException 如果加载FXML文件时发生IO异常
    */
    private void goMain(ActionEvent event) throws IOException {
        // 使用FXMLLoader加载主界面FXML文件，并创建根节点
        // Objects.requireNonNull确保资源不为null，如果为null则抛出NullPointerException
        Parent root = FXMLLoader.load(
            Objects.requireNonNull(getClass().getResource("/bd2swing/View/main/main.fxml"))
        );
        // 获取当前窗口的Stage对象
        // 通过事件源(event.getSource())获取触发事件的节点(Node)
        // 然后获取该节点所在的场景(Scene)，最后获取场景所在的窗口(Stage)
        Stage stage = (Stage) ((Node) event.getSource())
                .getScene().getWindow();
        // 将新加载的界面设置为当前场景的根节点，实现界面切换
        stage.getScene().setRoot(root);
    }
}

# 简介
java练习-中国象棋小游戏  
利用awt和swing库绘制界面，使用鼠标操作对战   
![ScreenShot_Main](img/ScreenShot_Main.png)  

# 联机
## 登录或注册页面
如果用户注册过，检查密码通过，则进入大厅   
如果没有注册过，直接保存密码，进入大厅
```mermaid
graph TD
    A[登录/注册页面] --> B[输入用户名]
    B --> C[输入密码]
    C --> D[提交按钮]
    D --> E{服务器验证}
    E -->|验证通过| F[进入大厅]
    E -->|验证失败| G[显示错误信息]
    G --> A 

```

```mermaid
graph TD
    A(用户密码校验) --> B{用户名是否存在}
    B -->|否| C{将用户名和密码存入数据库}
    C --> D[返回成功]
    B -->|是| E{密码是否正确}
    E -->|否| F[返回失败]
    E -->|是| D

```

## 大厅页面
大厅主要是为了创建房间和等待邀请
如果收到邀请，弹出邀请界面，如果确认，进入房间
点击创建房间，进入房间
点击加入游戏，随机进入空闲房间

## 房间页面
显示双方准备状态
如果双方都准备好，进入对战界面

## 对战界面
单击发送位置到服务器
服务器转发到对方
对方如果读取服务器发送的位置，更新显示

## 消息设计
消息类型：登录
用户名：
密码：

type
Login Username Password



# 参考
如何用面向对象设计一个程序，经典推荐？ - rufeng2000的回答 - 知乎
https://www.zhihu.com/question/36113705/answer/2405176382



```uml
@startuml
(*) --> "移动"
    --> "鼠标单击"
if "是否有棋子抬起" then
  -->[false] "棋子状态切成抬起"
  --> "棋子位置为鼠标位置"
else
  -->[true] if "是否能落下" then
            -->[true] "棋子状态切为落下"
            --> "棋子位置改为落点位置"
     endif


endif
@enduml

```
File -> Import -> Maven -> Existing Maven Project -> Browser -> Select ChineseChess Folder -> Finish
On Left Package Explorer Panel -> Right Click On this package -> Maven -> Update Project
然后就可以运行了

ubuntu 
1. 要安装maven和openjdk
sudo apt install maven openjdk-11-jdk
2. 设置eclipse源
Eclipse -> Window -> Preferences -> Install/Update -> Available Software Sites
https://mirrors.ustc.edu.cn/eclipse/releases/2023-09
http://mirrors.ustc.edu.cn/eclipse/releases/2023-09/202309131000/
3. 设置maven源
create ~/.m2/settings.xml
```xml
<settings>
    <mirrors>
        <mirror>
            <id>aliyun</id>
            <name>aliyun</name>
            <mirrorOf>central</mirrorOf>
            <!-- 国内推荐阿里云的Maven镜像 -->
            <url>https://maven.aliyun.com/repository/central</url>
        </mirror>
    </mirrors>
</settings>
```


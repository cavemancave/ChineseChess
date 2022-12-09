# ChineseChess

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

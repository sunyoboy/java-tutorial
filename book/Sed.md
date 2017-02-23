正则表达式
去掉html中的tags:
示例：
demo.html 
    
    <b>This</b> is what <span style="text-decoration: underline;">I</span> meant. Understand?

替换命令：

        # 如果你这样搞的话，就会有问题
        $ sed 's/<.*>//g' demo.html
         meant. Understand?
         
        # 要解决上面的那个问题，就得像下面这样。
        # 其中的'[^>]' 指定了除了>的字符重复0次或多次。
        $ sed 's/<[^>]*>//g' demo.html
        This is what I meant. Understand?
Ref: http://coolshell.cn/articles/9104.html

http://coolshell.cn/articles/5426.html
http://coolshell.cn/articles/11564.html
http://coolshell.cn/articles/9070.html
http://coolshell.cn/articles/8883.html
http://coolshell.cn/articles/8619.html
输出你最常用的十条命令
history|awk ‘{print $2}’|awk ‘BEGIN {FS=”|”} {print $1}’|sort|uniq -c|sort -rn|head -10)
Linux命令行与Shell脚本编程大全（第2版）
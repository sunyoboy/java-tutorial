Java使用Unicode处理所有字符串.

Locale对格式化进行控制
    * 语言
    * 位置
    * 脚本
    * 变体
    * 指定诸如方言或拼写之类

Java提供了一个格式化器(formatter)对象的集合,可以对java.text包中的数字值进行格式化和解析.
    NumberFormat的工厂方法
        getNumberInstance // 数字
        getCurrencyInstance // 货币量
        getPercentInstance // 百分比
    工厂方法只知道如何定位属于特定locale的对象


DateFormat

MessageFormat


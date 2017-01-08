cp -R /var/root/env/leetcode/mybatis/src/main/java/com/javase/mybatis/* .

http://dev.mysql.com/doc/sakila/en/

table resources<urls> 存放所有的资源,这里是URL地址例如/member/edit
table roles 存放所有的角色
table resource_roles 存放角色和资源之间的关系，及哪些角色拥有哪些资源的访问权限
table users 存放用户
table role_users 存放用户和角色之间的关系，及哪些用户拥有哪些角色。

这样最终就通过user->role->resource中转，可以得到每个用户拥有哪些资源的权限。

当然还有一个全局访问策略问题，例如默认是允许全部访问，还是全部禁止访问。然后resource_roles这个表里面可以有个字段定义当前记录是定义允许访问，还是禁止访问。最后将全局策略和查询出的具体访问定义取交并集，即得到最终的权限列表。
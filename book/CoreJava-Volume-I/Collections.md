
### 13.4.4 自己编写算法
  * 1 编写自己的算法（以集合作为参数的任何方法），应该尽可能地使用**接口**，而不要使用具体的实现。
  * 2 将集合接口作为方法参数。（Java类库中没有这样做的原因？时间问题，有些库是在集合类库之前创建的。）
  * 编写返回集合的方法，想要返回接口 －> 只需要返回AbstractList的匿名类
    List<JMenuItem> getAllItems(final JMenu menu) {
        return new AbstractList<>() {
            public JMenuItem get(int i) {
                return menu.getItem(i);
            }

            public int size() {
                return menu.getItemCount();
            }
        };
    }

# 13.5 遗留的集合
  Java问世就存在的集合类：Hashtable、Properties、Stack 及 BitSet

  Hashtable: 与HashMap作用一样，拥有相同的接口，线程安全
  Enumeration: 
  Properties: 
  Stack: 
  BitSet: 
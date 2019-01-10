## **散列**
散列表的实现常常叫做散列.
散列是一种用以常数平均时间执行插入,删除和查找的技术.
但是那些需要元素间任何排序信息的树操作(findMin,findMa...)将不会得到有效的支持.

1. 关键字: 通常查找是项的某个部分(数据域)行,这部分叫关键字
2. 散列表: 根据键值而直接访问数据的数组的数据结构
3. 散列函数: 把每个关键字映射到[0,tableSize-1]的范围的一个映射(函数)
    - 策略: 
        - 不同键值可以映射到同一位置
        - 表的大小选择为素数
    - 实现
        - 键值为字符串,把字符串中的ASCII码(或Unicode码)值加起来
            - 代码
        
                    public static int hash(String key,int tableSize){
                        int hashVal = 0;
                        for(int i = 0; i < key.length(); i++){
                            hashVal += key.charAt(i);
                        }
                        // hash的值的和对表大小取余
                        return hashVal % tableSize;
                    }
            - 缺点: 表很大时,因为ASCII码最大为127,字符串不是很长时,不能很好的均匀分配到表上
        - 前三个字母
            - 代码
            
                    public static int hash(String key, int tableSize){
                        return (key.charAt(0) + 27 * key.charAt(1) + 729 * key.charAt(2) ) % tableSize;
                    }
            - 含义: 27为26个英文字母加一个空格的个数,分别取字符串前三位,再分别与27^n相乘,n为字符下标
            - 缺点: 因为单词中前英文字母不是随机的,某词典表示,3个字母的不同组合数实际只有2851.
        - 计算所有字母
            - 代码
            
                    public static int hash(String key, int tableSize){
                        int hashVal = 0;
                        for(int i = 0; i < key.length(); i++){
                            hashVal = 37 * hashVal + key.charAt(i);
                        }
                        hashVal %= tableSize;
                        if(hashVal < 0){
                            hashVal += tableSize;
                        }
                        return hashVal;
                    }
4. λ: 散列表元素个数 / 散列表大小(即每个节点放多少个元素,即每个节点链表的长度)
5. 平均查找代价: 1 + λ / 2(所以散列表的大小实际并不重要)
5. 冲突
当一个元素被插入时与一个已经插入的元素散列到先相同的值,就会产生一个冲突.
    - 消除方法
        - 分离链接法
            1. 定义: 将散列到同一个值的所有元素保留到一个表中
            2. λ: 尽量使该值约等于1
        - 尝试另外一些单元(不使用额外表)
            1. 定义: 尝试另外一些单元,h0(x),h1(x),h2(x),...相继被尝试,hi(x) = (hash(x) + f(i)) mod TableSize,且f(0) = 0,函数f是冲突解决办法.
            2. 探测散列表: 对于不使用分离链接的散列表,其装填因子应该低于 λ = 0.5.
            3. 冲突解决方法:
                - 线性探测法: 函数f是i的线性函数,典型情形是f(i)=i.
                - 缺点: 随着元素增加,解决一次冲突(一次聚集)的时间越来越长
                
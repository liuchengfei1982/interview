package com.eagle.interview.IKM;

/**
 * 要点一： 相等（相同）的对象必须具有相等的哈希码（或者散列码）
 * 初学者可以简单理解，hashCode方法实际上返回的就是对象存储的物理地址
 *
 * 当集合要添加新的元素时，先调用这个元素的hashCode方法，
 * 就一下子能定位到它应该放置的物理位置上。如果这个位置上没有元素，
 * 它就可以直接存储在这个位置上，不用再进行任何比较了；
 * 如果这个位置上已经有元素了，就调用它的equals方法与新元素进行比较，
 * 相同的话就不存了，不相同就散列其它的地址。
 * 所以这里存在一个冲突解决的问题。
 * 这样一来实际调用equals方法的次数就大大降低了，几乎只需要一两次
 *
 * 想象一下，假如两个Java对象A和B，A和B相等（eqauls结果为true），
 * 但A和B的哈希码不同，则A和B存入HashMap时的哈希码计算得到的HashMap内部数组位置索引可能不同，
 * 那么A和B很有可能允许同时存入HashMap，显然相等/相同的元素是不允许同时存入HashMap，HashMap不允许存放重复元素。
 *
 * 要点二： 两个对象的hashCode相同，它们并不一定相同
 * 也就是说，不同对象的hashCode可能相同；假如两个Java对象A和B，A和B不相等（eqauls结果为false），
 * 但A和B的哈希码相等，将A和B都存入HashMap时会发生哈希冲突，
 * 也就是A和B存放在HashMap内部数组的位置索引相同这时HashMap会在该位置建立一个链接表，
 * 将A和B串起来放在该位置，显然，该情况不违反HashMap的使用原则，
 * 是允许的。当然，哈希冲突越少越好，尽量采用好的哈希算法以避免哈希冲突
 */
public class Q7 {
	public static void main(String[] args) {
		String str1 = "My String";
		String str2 = new String("My String");

		System.out.println(str1 == str2);

		/**
		 * str1和str2是String类型。String类型重写了hasCode和equal方法，因此String类型认为是相等的
		 *
		 * str1和str2如果是自己写的类型（如Student）就不一样了，
		 * 自己写的Student类并没有重新自己的hashcode()和equals()方法，所以在比较时，
		 * 是继承的object类中的hashcode()方法，而object类中的hashcode()方法是一个本地方法，
		 * 比较的是对象的地址（引用地址），使用new方法创建对象，两次生成的当然是不同的对象了，
		 * 造成的结果就是两个对象的hashcode()返回的值不一样，
		 * 所以Hashset会把它们当作不同的对象对待
		 *
		 * 解决这个问题呢？答案是：重新hashcode()和equals()方法
		 */
		System.out.println(str1.hashCode() == str2.hashCode()); //true
		System.out.println(str1.equals(str2));//true
		System.out.println(str1.matches(str2));//true
	}
}

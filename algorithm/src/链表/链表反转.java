package 链表;

import java.util.List;

public class 链表反转 {
	
	
	public static void main(String[] args) {
		List<String> strs = List.of("A", "B", "C", "D");
		// 创建单向链表
		ListNode singleLinkedList = createSingleLinkedList(strs);
		// 链表反转
		ListNode reverse = reverse(singleLinkedList);
		// 输出
		printListNode(reverse);
		
		
	}
	
	private static void printListNode(ListNode head) {
		ListNode temp = head;
		while (temp.next != null) { // 最后不会打印null
			System.out.println(temp.str);
			temp = temp.next;
		}
		
	}

	public static ListNode createSingleLinkedList(List<String> strs) {
		ListNode head = new ListNode(null);
		ListNode temp = head;
		for (String s : strs) {
			ListNode cur = new ListNode(s);
			temp.next = cur;
			temp = temp.next; 
		}
		return head;
	}
	
	/**
	 * a -> b -> c -> d
	 * 1.先保存当前结点的下一个结点
	 * 2.当前结点的next指针指向pre
	 * 3.pre指针指向cur结点
	 * 4.cur指向next结点
	 * 
	 * @param head
	 * @return
	 */
	public static ListNode reverse(ListNode head) {
		
		// cur最后为null，pre为最后一个结点
		ListNode pre = null, cur = head, next;
		
		while(cur != null) {
			next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}
		
		return pre;
		
	}

}

class ListNode{
	
	public String str;
	public ListNode next;
	
	public ListNode(String str) {
		this.str = str;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public ListNode getNext() {
		return next;
	}
	public void setNext(ListNode next) {
		this.next = next;
	}
	
}

package ����;

import java.util.List;

public class ����ת {
	
	
	public static void main(String[] args) {
		List<String> strs = List.of("A", "B", "C", "D");
		// ������������
		ListNode singleLinkedList = createSingleLinkedList(strs);
		// ����ת
		ListNode reverse = reverse(singleLinkedList);
		// ���
		printListNode(reverse);
		
		
	}
	
	private static void printListNode(ListNode head) {
		ListNode temp = head;
		while (temp.next != null) { // ��󲻻��ӡnull
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
	 * 1.�ȱ��浱ǰ������һ�����
	 * 2.��ǰ����nextָ��ָ��pre
	 * 3.preָ��ָ��cur���
	 * 4.curָ��next���
	 * 
	 * @param head
	 * @return
	 */
	public static ListNode reverse(ListNode head) {
		
		// cur���Ϊnull��preΪ���һ�����
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

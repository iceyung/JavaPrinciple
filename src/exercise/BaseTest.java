package exercise;

import java.util.Stack;

public class BaseTest {
	public static void main(String[] args) {
		// printPrime();
		// System.out.println();
		// printPrime2();
		// System.out.println();

		int[] A = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int[] B = { 2, 9, 8, 7, 6, 5, 4, 3, 1 };
		int[] C = { 2, 9, 8, 7, 6, 5, 3, 4, 1 };
		System.out.println(isOutStack(A, B));
		System.out.println(isOutStack(A, C));
	}

	/**
	 * 打印素数方法1
	 */
	public static void printPrime() {
		System.out.print(2); // 2是偶数又是奇数
		for (int i = 3; i <= 100; i += 2) { // 只判断奇数
			int j = 0;
			for (j = 2; j < Math.sqrt(i); j++) { // 小于根号下i
				if (i % j == 0)
					break;
			}
			if (j > Math.sqrt(i))
				System.out.print(" " + i);
		}
	}

	/**
	 * 打印素数方法2:暴力破解
	 */
	public static void printPrime2() {
		boolean IS_Prime = true;
		for (int i = 2; i <= 100; i++) {
			int j = 0;
			IS_Prime = true;
			for (j = 2; j <= i / 2; j++) {
				if (i % j == 0) {
					IS_Prime = false;
					break;
				}
			}
			if (i == j || IS_Prime)
				System.out.print(i + " ");
		}
	}

	/**
	 * 给定入栈顺序，给出一组出栈顺序，判断是否满足条件。如入栈 ：12345 出栈：21453
	 * 算法 需要一个栈作为辅助:
	 * 设置两个指针in , out表示需入栈元素和需出栈元素的位置,
	 * 循环判断out是否已经>=需入栈元素序列长度；out已经>=需入栈元素序列长度表示满足条件
	 *   1. 需入栈元素未入栈完毕:
	 *     1.1 需入栈元素和需出栈元素相等: in++,out++
	 *     1.2 需入栈元素和需出栈元素不相等：
	 *       1.2.1 栈空：需入栈元素入栈
	 *       1.2.2 栈不空:如果栈顶元素和需出栈元素相等，出栈并out++;如果栈顶元素和需出栈元素不相等， 需入栈元素入栈;
	 *   2. 需入栈元素已入栈完毕:  
	 *    栈内元素依次出栈，出栈元素与需出栈元素不相等则表示不满足条件。
	 * @param inBound
	 * @param outBound
	 * @return
	 */
	public static boolean isOutStack(int[] inBound, int[] outBound) {
		if (inBound == null || outBound == null)
			return false;

		Stack<Integer> stack = new Stack<Integer>();
		int in = 0; // 进栈的下标
		int out = 0; // 出栈的下标
		while (out != outBound.length) {
			if (in < inBound.length) { // 需入栈元素未入栈完毕
				if (inBound[in] == outBound[out]) { // 需入栈元素和需出栈元素相等
					in++;
					out++;
				} else if (stack.isEmpty()) { // 栈为空
					stack.push(inBound[in++]);
				} else { // 栈不为空
					if (stack.peek() == outBound[out]) { // 栈顶元素与需出栈元素相等
						out++;
						stack.pop();
					} else { // 栈顶元素与需出栈元素不相等
						stack.push(inBound[in++]);
					}
				}
			} else { // 需入栈元素已入栈完毕
				if (stack.pop() == outBound[out++]) // 栈内元素依次出栈
					continue;
				else
					return false;
			}
		}
		return true;
	}

}

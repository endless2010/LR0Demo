public class Transform {

	public String[] transfer(String s) {

		String[] tt = new String[50];
		StringBuffer[] strBuf = new StringBuffer[50];
		for (int i = 0; i < strBuf.length; i++)
			strBuf[i] = new StringBuffer();
		int j = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '>') {
				strBuf[j].append(">");

			} else if (s.charAt(i) == '\n' || s.charAt(i) == ' ') {
				if (s.charAt(i - 1) != ' ')
					j++;
			} else
				strBuf[j].append(s.charAt(i));

		}
		for (int i = 0; i < strBuf.length; i++) {
			tt[i] = strBuf[i].toString();
		}
		return tt;

	}

	public static void main(String[] args) {
		String s = "S>ABC A>a\nB>b\nC>c";
		Transform t = new Transform();
		String[] p = new String[50];
		p = t.transfer(s);
		for (int i = 0; i < p.length; i++) {
			System.out.println(p[i]);
		}
		System.out.println();
	}
}
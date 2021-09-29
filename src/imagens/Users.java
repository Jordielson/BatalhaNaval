package imagens;

import java.net.URL;

public class Users {	
	private static final URL ICON_USER = Users.class.getResource("/resources/imagens/user.png");
	private static final URL ICON_USER2 = Users.class.getResource("/resources/imagens/user2.png");
	private static final URL ICON_USER3 = Users.class.getResource("/resources/imagens/user3.png");
	private static final URL ICON_USER4 = Users.class.getResource("/resources/imagens/user4.png");
	private static final URL ICON_USER5 = Users.class.getResource("/resources/imagens/user5.png");
	private static final URL ICON_USER6 = Users.class.getResource("/resources/imagens/user6.png");
	private static final URL ICON_USER7 = Users.class.getResource("/resources/imagens/user7.png");
	private static final URL ICON_USER8 = Users.class.getResource("/resources/imagens/user8.png");
	private static final URL ICON_USER9 = Users.class.getResource("/resources/imagens/user9.png");
	private static final URL ICON_USER10 = Users.class.getResource("/resources/imagens/user10.png");
	private static final URL ICON_USER11 = Users.class.getResource("/resources/imagens/user11.png");
	private static final URL ICON_USER12 = Users.class.getResource("/resources/imagens/user12.png");
	
	public static URL obterIcon() {
		URL[] icons = {ICON_USER, ICON_USER2, ICON_USER3, ICON_USER4, ICON_USER5, ICON_USER6, ICON_USER7, 
				ICON_USER8, ICON_USER9, ICON_USER10, ICON_USER11, ICON_USER12};
		int p = (int) (Math.random()*icons.length);
		return icons[p];
	}
}

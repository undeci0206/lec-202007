package kr.or.ddit.enumpkg;

import kr.or.ddit.vo.MenuVO;

public enum ServiceKind{
//하드코딩하지 않고 MenuVO만들어서 동적으로 변경되게 해보자.
	GUGUDAN(MenuVO.getBuilder()
			.menuText("구구단")
			.menuPath("/02/gugudan.jsp")
			.build()),
	LOCALEMESSAGE(MenuVO.getBuilder()
			.menuText("로케일")
			.menuPath("/02/messageLocale.jsp")
			.build()),
	CALCULATOR(MenuVO.getBuilder()
			.menuText("계산기")
			.menuPath("/03/calculateFormAjax.jsp")
			.build()),
	CALENDER(MenuVO.getBuilder()
			.menuText("달력")
			.menuPath("/04/calender.jsp")
			.build()),
	IMPLICITOBJECT(MenuVO.getBuilder()
			.menuText("기본객체")
			.menuPath("/06/implicitObject.jsp")
			.build()),
	BTS(MenuVO.getBuilder()
			.menuText("방탄")
			.menuURI("/bts") //모델 2. ModulizationServlet을 타지 않아도 됨!
			.build()),
	SESSIONTIMER(MenuVO.getBuilder()
			.menuText("세션타이머")
			.menuURI("/sessionTimer.do")
			.build()),
	IMAGELIST(MenuVO.getBuilder()
			.menuText("imageList")
			.menuURI("/imageList")
			.build());
	
	private MenuVO menu;

	private ServiceKind(MenuVO menu) {
		this.menu = menu;
	}
	
	public MenuVO getMenu() {
		return menu;
	}
}

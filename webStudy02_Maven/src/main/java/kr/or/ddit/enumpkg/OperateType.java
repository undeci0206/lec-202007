package kr.or.ddit.enumpkg;


public enum OperateType {

	//람다식 씀. 혹시 예전 버전이라서 람다식 못 쓴다면?? 익명 객체 쓸 수 있다.
	PLUS('+', (l, r)->{
		return l+r;
	}), MINUS('-', (l, r)->{
		return l-r;
	}), MULTIPLY('*', new RealOperator() { //이름이 없는 인스턴스를 만듦 -> 익명 객체
		public int operate(int l, int r) {
			return l * r;
		}
	}), DIVIDE('/', (l, r)->{
		return l/r;
	}),	MODULAR('%',(l, r)-> {
		return 1%r;
	});

	//기능적 함수의 인터페이스. functional interface. (인터페이스 : 메서드 1:1관계)
	@FunctionalInterface
	public static interface RealOperator{
		public int operate(int leftOp, int rightOp);
	}

	private char sign;
	private RealOperator realOperator;
	private OperateType(char sign, RealOperator realOperator) {
		this.sign = sign;
		this.realOperator = realOperator;
	}

	//getter
	public char getSign() {
		return sign;
	}

	//상수가 결정되기 전에는 operator의 연산을 결정할 수 없다. -> 행위(연산)에 대한 정보를 생성자에서 받아야 한다.
	//상수가 결정될 때, 객체가 해야 하는 일까지 결정되어야 한다. 행위 자체를 넘기는 건 불가능하기 때문에, functional interface를 만들어야 한다.
	//(위에 있는 RealOperator)
	//**RealOperator에게 연산을 떠넘기면 된다. RealOperator->상수가 정의될 때만 생성자가 호출이 된다.
	public int operator(int leftOp, int rightOp) {
		return realOperator.operate(leftOp, rightOp);
	}
}


package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="lprod_id")

public class LprodVO implements Serializable{
	private String lprod_id;
	private String lprod_gu;
	private String lprod_nm;
	
}

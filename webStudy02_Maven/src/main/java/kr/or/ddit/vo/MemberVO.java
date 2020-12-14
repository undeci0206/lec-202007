package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of= {"mem_id", "mem_regno1", "mem_regno2"})
@ToString(exclude = {"mem_pass", "mem_regno1", "mem_regno2"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberVO implements Serializable{
	private String mem_id;
	private transient String mem_pass;
	private String mem_name;
	private transient String mem_regno1;
	private transient String mem_regno2;
	private String mem_bir;
	private String mem_zip;
	private String mem_add1;
	private String mem_add2;
	private String mem_hometel;
	private String mem_comtel;
	private String mem_hp;
	private String mem_mail;
	private String mem_job;
	private String mem_like;
	private String mem_memorial;
	private String mem_memorialday;
	private Integer mem_mileage;
	private String mem_delete;
	
}

package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.validate.rule.TelNumber;
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
	@NotBlank(groups= {Default.class, DeleteGroup.class})
	private String mem_id;
	@NotBlank(groups= {Default.class, DeleteGroup.class})
	@Size(min=5, max=12)
	private transient String mem_pass;
	@NotBlank //Default이므로 생략 가능
	private String mem_name;
	@NotBlank(groups=InsertGroup.class)
	@Pattern(regexp="[0-9] {6}")
	private transient String mem_regno1;
	@NotBlank(groups=InsertGroup.class)
	@Pattern(regexp="[0-9] {7}")
	private transient String mem_regno2;
	private String mem_bir;
	@NotBlank
	private String mem_zip;
	@NotBlank
	private String mem_add1;
	@NotBlank
	private String mem_add2;
	@NotBlank
	@TelNumber
	private String mem_hometel;
	@NotBlank
	@TelNumber
	private String mem_comtel;
	@TelNumber(value="\\d{2,3} \\d{3,4} \\d{4}", placeholder="000 0000 0000")
	private String mem_hp;
	@NotBlank
	@Email
	private String mem_mail;
	private String mem_job;
	private String mem_like;
	private String mem_memorial;
	private String mem_memorialday;
	@Min(0) //single value annotation 정의. 0보다 큰 값
	@Max(Integer.MAX_VALUE)
	private Integer mem_mileage;
	private String mem_delete;
	
	private List<ProdVO> prodList; // Member has many Prod (1:n, has many 관계)
	
}

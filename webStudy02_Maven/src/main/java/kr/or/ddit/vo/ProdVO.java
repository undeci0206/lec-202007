package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = "prod_id")
@ToString(exclude="prod_detail")
public class ProdVO implements Serializable {
	@NotBlank(groups=UpdateGroup.class)
	@Size(max = 10)
	private String prod_id;
	@NotBlank
	@Size(max = 40)
	private String prod_name;
	@NotBlank
	@Size(max = 4)
	private String prod_lgu;
	@NotBlank
	@Size(max = 6)
	private String prod_buyer;
	@NotNull
	@Min(0)
	private Integer prod_cost;
	@NotNull
	@Min(0)
	private Integer prod_price;
	@NotNull
	@Min(0)
	private Integer prod_sale;
	@NotBlank
	@Size(max = 100)
	private String prod_outline;
	private String prod_detail;
	@NotBlank
	@Size(max = 40)
	private String prod_img;
	@NotNull
	@Min(0)
	private Integer prod_totalstock;
	private String prod_insdate;
	@NotNull
	@Min(0)
	private Integer prod_properstock;
	@Size(max = 20)
	private String prod_size;
	@Size(max = 20)
	private String prod_color;
	@Size(max = 255)
	private String prod_delivery;
	@Size(max = 6)
	private String prod_unit;
	private Integer prod_qtyin;
	private Integer prod_qtysale;
	private Integer prod_mileage;

	private BuyerVO buyer; // Prod has a Buyer
	private Set<MemberVO> memberList;
}

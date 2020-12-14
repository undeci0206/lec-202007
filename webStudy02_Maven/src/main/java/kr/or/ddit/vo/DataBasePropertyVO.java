package kr.or.ddit.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Database property 하나의 정보를 담기위한 Domain Layer 
 */
//@Getter
//@Setter
@EqualsAndHashCode(of= {"property_name"}) //multi value annotation
@ToString(exclude= {"bytes"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataBasePropertyVO implements Serializable{ //직렬화
	private String property_name;
	private String property_value;
	private String description;
	
	@JsonIgnore //json 시 마샬링 대상에서 제외됨
	private transient byte[] bytes; //@ToString시 이런 형태 안됨.. 이것까지 출력하려고 함. 제외시켜보자
	
}

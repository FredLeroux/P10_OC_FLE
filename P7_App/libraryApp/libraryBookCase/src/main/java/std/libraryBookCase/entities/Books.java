package std.libraryBookCase.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "books", schema = "libraryum")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Books implements Serializable {


	private static final long serialVersionUID = 7123011051900300385L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "kind")
	private String kind;

	@Column(name = "title")
	private String title;

	@Column(name = "author")
	private String author;

	@Column(name = "availability")
	private Boolean availability;

	@Column(name = "library_building_fk")
	private Integer libraryBuildingFk;

}

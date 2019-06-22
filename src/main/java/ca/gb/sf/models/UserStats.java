package ca.gb.sf.models;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

import org.springframework.data.annotation.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;

@SqlResultSetMapping(
    name = "reportEducatorWithCountQuery",
    classes = {
        @ConstructorResult(
            targetClass = ca.gb.sf.models.UserStats.class,
            columns = {
                @ColumnResult(name = "Id", type=Long.class),
                @ColumnResult(name = "Name", type=String.class),
                @ColumnResult(name = "AssignmentCount"),
                @ColumnResult(name = "AssignmentCompletedCount")
	        }
        )
    }
)

@NamedNativeQueries(
    {
        @NamedNativeQuery(
		    name = "reportEducatorWithCountQuery",
			query = "SELECT "
                + "  u.id as id, "
                + "  u.display_name as name, "
                + "  (select count(*) from Assignments ar WHERE ar.user_id = u.id) as assignment_count, "
                // + "  (select count(*) from Assignments ar WHERE ar.user_id = u.id AND ar.code = 'COMPLETED') as assignment_completed_count "
                + "  (select count(*) from Assignments ar WHERE ar.user_id = u.id AND ar.assignment_status_id = (SELECT a.id FROM Assignment_Status a WHERE a.code = 'COMPLETED')) as assignment_completed_count "
                + "FROM "
                + "  Users u "
                + "WHERE "
                + "  u.educator_id = :educatorId", resultClass = ca.gb.sf.models.UserStats.class
        )
    }
)

@Entity
@Immutable
public class UserStats {

	@Id
	private long id;
	
	private String name;
	
	private long assignmentCount;
	
	private long assignmentCompletedCount;
	
	public UserStats() {};
	
	public UserStats(long id, String name, long assignmentCount, long assignmentCompletedCount) {
		super();
		this.id = id;
		this.name = name;
		this.assignmentCount = assignmentCount;
		this.assignmentCompletedCount = assignmentCompletedCount;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getAssignmentCount() {
		return assignmentCount;
	}

	public void setAssignmentCount(long assignmentCount) {
		this.assignmentCount = assignmentCount;
	}

	public long getAssignmentCompletedCount() {
		return assignmentCompletedCount;
	}

	public void setAssignmentCompletedCount(long assignmentCompletedCount) {
		this.assignmentCompletedCount = assignmentCompletedCount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserStats [id=");
		builder.append(id);
		builder.append(", ");
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		builder.append("assignmentCount=");
		builder.append(assignmentCount);
		builder.append(", assignmentCompletedCount=");
		builder.append(assignmentCompletedCount);
		builder.append("]");
		return builder.toString();
	}
	
}

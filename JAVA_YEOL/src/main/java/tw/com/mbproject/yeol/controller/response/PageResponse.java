package tw.com.mbproject.yeol.controller.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageResponse {
    
    /** 總筆數 */
    private Long totalRecords;
    /** 總頁數 */
    private Integer totalPages;
    /** 每頁筆數 */
    private Integer pageSize;
    /** 目前頁號 */
    private Integer pageNumber;
    /** 目前頁筆數 */
    private Integer pageRecords;
  

}

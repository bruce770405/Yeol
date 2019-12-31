package tw.com.mbproject.yeol.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings({"rawtypes"})
@Getter
@Setter
public class PageDto<T extends List> {
    
    /** 總頁數 */
    private Integer totalPages;
    /** 總筆數 */
    private Long totalRecords;
    /** 每頁筆數 */
    private Integer pageSize;
    /** 目前頁號 */
    private Integer pageNumber;
    /** 目前頁筆數 */
    private Integer pageRecords;
    
    private T data;
    
    private PageDto() {
        
    }
    
    public PageDto(Page page) {
        this.totalPages = page.getTotalPages();
        this.totalRecords = page.getTotalElements();
        this.pageSize = page.getSize();
        this.pageNumber = page.getNumber();
        this.pageRecords = page.getNumberOfElements();
    }
    
    public static PageDto empty() {
        PageDto pageDto = new PageDto();
        return pageDto;
    }

}

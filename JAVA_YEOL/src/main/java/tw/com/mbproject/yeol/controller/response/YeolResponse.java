package tw.com.mbproject.yeol.controller.response;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import tw.com.mbproject.yeol.controller.response.code.ErrCode;
import tw.com.mbproject.yeol.dto.PageDto;
import tw.com.mbproject.yeol.exception.ResponseException;

public class YeolResponse<T> {

    @Getter
    private final String code;

    @Getter
    private final String msg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "data")
    private T data;

    /**
     * 總筆數
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Getter
    private Long totalRecords;
    /**
     * 總頁數
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Getter
    private Integer totalPages;
    /**
     * 每頁筆數
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Getter
    private Integer pageSize;
    /**
     * 目前頁號
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Getter
    private Integer pageNumber;
    /**
     * 目前頁筆數
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Getter
    private Integer pageRecords;

    public YeolResponse() {
        this.code = ErrCode.SUCCESS.getCode();
        this.msg = ErrCode.SUCCESS.getMsg();
    }

    public YeolResponse(ErrCode errCode) {
        if (StringUtils.isAnyBlank(errCode.getCode(), errCode.getMsg())) {
            throw new ResponseException(ErrCode.RESPONSE_NO_ERROR_CODE);
        }
        this.code = errCode.getCode();
        this.msg = errCode.getMsg();
    }

    public YeolResponse(T data, ErrCode errCode) {
        this.code = errCode.getCode();
        this.msg = errCode.getMsg();
        this.data = data;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public YeolResponse(PageDto pageDto, ErrCode errCode) {
        this.code = errCode.getCode();
        this.msg = errCode.getMsg();
        this.totalPages = pageDto.getTotalPages();
        this.totalRecords = pageDto.getTotalRecords();
        this.pageSize = pageDto.getPageSize();
        this.pageNumber = pageDto.getPageNumber();
        this.pageRecords = pageDto.getPageRecords();
        this.data = (T) pageDto.getData();
    }

}

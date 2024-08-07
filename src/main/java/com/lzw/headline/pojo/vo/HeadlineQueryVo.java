package com.lzw.headline.pojo.vo;

/**
 * @Author: lzw
 * @Description: TODO
 * @Date: 2024/8/7 16:41
 * @Version: 1.0
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HeadlineQueryVo implements Serializable {
    private String keyWords;
    private Integer type ;
    private Integer pageNum;
    private Integer pageSize;
}
package com.lzw.headline.pojo;

/**
 * @Author: lzw
 * @Description: TODO
 * @Date: 2024/8/7 16:39
 * @Version: 1.0
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewsType implements Serializable {
    private Integer tid;
    private String tname;
}

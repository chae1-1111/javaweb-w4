package org.zerock.b01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;

    private String type;
    private String keyword;
    private String link;

    public String[] getTypes() {
        if(this.type == null || type.isEmpty()) {
            return null;
        }
        return type.split("");
    }

    public Pageable getPageable(String...props) {
        return PageRequest.of(this.page-1, this.size, Sort.by("bno").descending());
    }

    public String getLink() {

        if(link == null) {
            StringBuilder builder = new StringBuilder();

            builder.append("page="+this.page);
            if(this.size != 10) builder.append("&size="+this.size);
            if(this.type != null && !this.type.isEmpty()) builder.append("&type="+this.type);
            if(this.keyword != null && this.keyword.length() != 0) {
                try{
                    builder.append("&keyword=" + URLEncoder.encode(this.keyword,"UTF-8"));
                } catch (UnsupportedEncodingException e) {}
            }

            this.link = builder.toString();

        }
        return this.link;
    }

}

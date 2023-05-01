package com.wav.rzpp.mapper;

import com.wav.rzpp.entity.RzComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: hbw
 **/
@Mapper
public interface RzCommentMapper {
    void addComment(RzComment rzComment);

    List<RzComment> getAllComment();
}

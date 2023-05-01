package com.wav.rzpp.service;

import com.wav.rzpp.entity.RzComment;

import java.util.List;

/**
 * @author: hbw
 **/
public interface RzCommentService {
    void addComment(RzComment rzComment);

    List<RzComment> getAllComment();
}

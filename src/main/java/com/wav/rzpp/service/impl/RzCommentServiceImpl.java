package com.wav.rzpp.service.impl;

import com.wav.rzpp.entity.RzComment;
import com.wav.rzpp.mapper.RzCommentMapper;
import com.wav.rzpp.service.RzCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: hbw
 **/
@Service
public class RzCommentServiceImpl implements RzCommentService {
    @Autowired
    private RzCommentMapper rzCommentMapper;

    @Override
    public void addComment(RzComment rzComment) {
        rzCommentMapper.addComment(rzComment);
    }

    @Override
    public List<RzComment> getAllComment() {
        return rzCommentMapper.getAllComment();
    }
}

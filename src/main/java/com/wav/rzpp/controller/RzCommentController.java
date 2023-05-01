package com.wav.rzpp.controller;

import com.wav.rzpp.common.AjaxResult;
import com.wav.rzpp.entity.RzComment;
import com.wav.rzpp.service.RzCommentService;
import com.wav.rzpp.utils.SnowFlake;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: hbw
 **/
@CrossOrigin
@Api("评论模块")
@Validated
@RestController
@RequestMapping("/comment")
public class RzCommentController {
    @Autowired
    RzCommentService rzCommentService;

    @PostMapping("/addComment")
    public AjaxResult addComment(RzComment rzComment) {
        rzComment.setCommentId(SnowFlake.nextId());
        rzComment.setCreateTime(System.currentTimeMillis());
        rzCommentService.addComment(rzComment);
        return AjaxResult.success();
    }

    @PostMapping("/getAllComment")
    public AjaxResult getAllComment() {
        List<RzComment> rzCommentList = rzCommentService.getAllComment();
        return AjaxResult.success(rzCommentList);
    }
}

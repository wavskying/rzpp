package com.wav.rzpp.service.impl;

import com.wav.rzpp.entity.RzPosition;
import com.wav.rzpp.mapper.RzPositionMapper;
import com.wav.rzpp.service.RzPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author: hbw
 **/
@Service
public class RzPositionServiceImpl implements RzPositionService {

    @Autowired
    private RzPositionMapper rzPositionMapper;

    @Override
    public Integer addRzPosition(RzPosition rzPosition) {
        rzPosition.setCreateTime(System.currentTimeMillis());
        return rzPositionMapper.addRzPosition(rzPosition);
    }

    @Override
    public Integer addRzPositionList(List<RzPosition> rzPositionList) {
        return rzPositionMapper.addRzPositionList(rzPositionList);
    }

    @Override
    public List<Map<String, Object>> getAllRzPosition() {
        List<Map<String, Object>> result = new ArrayList<>();
        List<RzPosition> rzPositionList = rzPositionMapper.getAllRzPosition();
        // 转换成树形结构
        for (RzPosition rzPosition : rzPositionList) {
            if (rzPosition.getPid() == 0L) {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("id", rzPosition.getPositionId().toString());
                hashMap.put("name", rzPosition.getName());
                List<Map<String, Object>> childList = buildRzPositionTree(rzPosition.getPositionId(), rzPositionList);
                hashMap.put("children", childList);
                result.add(hashMap);
            }
        }
        return result;
    }

    @Override
    public void updatePosition(String id, String name) {
        rzPositionMapper.updatePosition(id, name);
    }

    @Override
    public void deletePosition(String id) {
        rzPositionMapper.deletePosition(id);
    }

    /**
     * 将 RzPosition 列表转换成树形结构
     *
     * @param pid            父节点的 ID
     * @param rzPositionList RzPosition 列表
     * @return 树形结构
     */
    private List<Map<String, Object>> buildRzPositionTree(Long pid, List<RzPosition> rzPositionList) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (RzPosition rzPosition : rzPositionList) {
            if (Objects.equals(rzPosition.getPid(), pid)) {
                HashMap<String, Object> stringObjectHashMap = new HashMap<>();
                stringObjectHashMap.put("id", rzPosition.getPositionId().toString());
                stringObjectHashMap.put("name", rzPosition.getName());
                List<Map<String, Object>> list = buildRzPositionTree(rzPosition.getPositionId(), rzPositionList);
                if (list.size() != 0) {
                    stringObjectHashMap.put("children", list);
                }
                result.add(stringObjectHashMap);
            }
        }
        return result;
    }
}

package com.stylefeng.guns.rest.modular.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.stylefeng.guns.rest.common.persistence.dao.MtimeAreaDictTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.MtimeBrandDictTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.MtimeCinemaTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.MtimeHallDictTMapper;
import com.stylefeng.guns.rest.common.persistence.model.MtimeBrandDictT;
import com.stylefeng.guns.rest.model.cinema.bo.AreaBo;
import com.stylefeng.guns.rest.model.cinema.bo.BrandBo;
import com.stylefeng.guns.rest.model.cinema.bo.CinemaBo;
import com.stylefeng.guns.rest.model.cinema.bo.HallTypeBo;
import com.stylefeng.guns.rest.model.cinema.requestvo.RequestCinemasVo;
import com.stylefeng.guns.rest.modular.cinema.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Ethan New
 * @Date: 2019/6/4 17:03
 * @Description:
 */

@Component
@Service(interfaceClass = CinemaService.class)
public class CinemaServiceImpl implements CinemaService {

    @Autowired
    MtimeCinemaTMapper cinemaMapper;
    @Autowired
    MtimeBrandDictTMapper brandMapper;
    @Autowired
    MtimeAreaDictTMapper areaMapper;
    @Autowired
    MtimeHallDictTMapper hallMapper;

    /**
     * 根据条件查询所有影院
     * @param requestCinemasVo
     * @return
     */
    @Override
    public List<CinemaBo> getPageCinemas(RequestCinemasVo requestCinemasVo) {
        PageHelper.startPage(requestCinemasVo.getNowPage(), requestCinemasVo.getPageSize());
        List<CinemaBo> cinemaBos = cinemaMapper.getPageCinemas(requestCinemasVo);
        return cinemaBos;
    }

    /**
     * 根据条件查询所有影院的总页数
     * @param requestCinemasVo
     * @return
     */
    @Override
    public int getTotalPage(RequestCinemasVo requestCinemasVo) {
        int total = cinemaMapper.getTotalPage(requestCinemasVo);
        int pageTotal = (int) Math.ceil(1.0 * total / requestCinemasVo.getPageSize());
        return pageTotal;
    }

    /**
     * 获取影院列表查询条件之影院条件
     * @param brandId
     * @return
     */
    @Override
    public List<BrandBo> getBrandList(Integer brandId) {
        List<BrandBo> brandBos = brandMapper.getBrandList(brandId);
        return brandBos;
    }

    /**
     * 获取影院列表查询条件之影厅条件
     * @param areaId
     * @return
     */
    @Override
    public List<AreaBo> getAreaList(Integer areaId) {
        List<AreaBo> areaBos = areaMapper.getAreaList(areaId);
        return areaBos;
    }

    /**
     * 获取影院列表查询条件之行政区条件
     * @param hallType
     * @return
     */
    @Override
    public List<HallTypeBo> getHallTypeList(Integer hallType) {
        List<HallTypeBo> hallTypeBos = hallMapper.getHallTypeList(hallType);
        return hallTypeBos;
    }
}
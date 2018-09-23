package spring.cloud.biz.dataaccess.mapper;

import org.apache.ibatis.annotations.Select;
import spring.cloud.biz.dataaccess.dataobject.MomentDo;

import java.util.List;

public interface MomentDoMapper {
    /**
     * delete moment by id
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @param record
     * @return
     */
    int insert(MomentDo record);

    /**
     * @param record
     * @return
     */
    int insertSelective(MomentDo record);

    /**
     * @param id
     * @return
     */
    MomentDo selectByPrimaryKey(Long id);

    /**
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(MomentDo record);

    /**
     * @param record
     * @return
     */
    int updateByPrimaryKey(MomentDo record);

    /**
     * @return
     */
    @Select("select * from moment where is_deleted=0")
    List<MomentDo> listMoment();
}
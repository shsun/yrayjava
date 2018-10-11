package spring.cloud.pusher.dataaccess.mapper;

import org.apache.ibatis.annotations.Select;
import spring.cloud.pusher.dataaccess.dataobject.MomentDo;
import spring.cloud.pusher.dataaccess.dataobject.ZMomentDo;

import java.util.List;

public interface MomentDoMapper extends ZMomentDoMapper {
    /**
     * @return
     */
    @Select("select * from moment where is_deleted=0")
    List<ZMomentDo> listMoment();
}
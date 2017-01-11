package test.IDao;

import test.domain.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer userno);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer userno);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}
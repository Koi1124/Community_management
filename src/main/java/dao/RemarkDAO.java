package dao;

import model.Remark;
import model.User;

import java.util.List;

public interface RemarkDAO {

    /**
     *@Discription: 操作留言，进行增
     *@Param: []
     *@Return: int
     *@Author:
     */
    int addRemark(Remark remark);

    /**
     *@Discription: 操作留言，进行改
     *@Param: []
     *@Return: int
     *@Author: 
     */
    int updateRemark(Remark remark);
    
    
    /**
     *@Discription: 操作留言，进行删
     *@Param: []
     *@Return: int
     *@Author: 
     */
    int deleteRemark(String rNum);

    /**
     *@Discription: 根据留言获取留言用户信息（社团详情页用）
     *@Param: [remarkNum]
     *@Return: java.util.List<model.User>
     *@Author:
     */

    User getCommentUserInfo(String rNum);
    
    
    /**
     *@Discription: 根据社团号获取留言（社团详情页用）
     *@Param: [cNum]
     *@Return: model.Remark
     *@Author: 
     */
    List<Remark> getRemarkInfo(String cNum);

}

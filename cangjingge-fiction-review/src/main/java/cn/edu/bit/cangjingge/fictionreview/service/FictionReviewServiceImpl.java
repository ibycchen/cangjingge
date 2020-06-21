package cn.edu.bit.cangjingge.fictionreview.service;

import cn.edu.bit.cangjingge.common.entity.FictionReview;
import cn.edu.bit.cangjingge.common.response.Response;
import cn.edu.bit.cangjingge.common.response.ResponseUtil;
import cn.edu.bit.cangjingge.fictionreview.FictionReviewDao.FictionReviewDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FictionReviewServiceImpl {
    @Resource
    FictionReviewDao fictionReviewDao;

    public Response getFictionReviewById(Long id) {
        FictionReview fictionReview = fictionReviewDao.getFictionReviewById(id);
        if (fictionReview == null)
            return ResponseUtil.error(1003, "评论不存在");
            //throw new BusinessException(ResponseStatusEnum.FICTION_REVIEW_NOT_FOUND);
        return ResponseUtil.success(fictionReview);
    }

    public Response<List<FictionReview>> getFictionReviewByFictionId(Long fictionId) {
        List<FictionReview> fictionReviews = fictionReviewDao.getFictionReviewByFictionId(fictionId);
        return ResponseUtil.success(fictionReviews);
    }

    public Response createFictionReview(Long fictionId, Long userId, Integer rate, String content){
        FictionReview fictionReview = new FictionReview();
        fictionReview.setFictionId(fictionId);
        fictionReview.setUserId(userId);
        fictionReview.setRate(rate);
        fictionReview.setContent(content);
        if (fictionReviewDao.putFictionReview(fictionReview))
            return ResponseUtil.success(fictionReview);
        return ResponseUtil.error(1006, "评论失败");
    }

    public Response deleteFictionReviewById(Long id) {
        if (fictionReviewDao.deleteFictionReviewById(id)) return ResponseUtil.success();
        return ResponseUtil.error(1003, "评论不存在");
    }

}

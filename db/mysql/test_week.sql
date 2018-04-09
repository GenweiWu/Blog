-- 用来测试根据周统计的能力
DROP TABLE IF EXISTS `t_test_week`;

CREATE TABLE `t_test_week` (
  `createTime` DATETIME NOT NULL,
  `remark`     VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`createTime`)
);


INSERT INTO `t_test_week` (`createTime`, `remark`) VALUES ('2015/12/27', '最后一周');
INSERT INTO `t_test_week` (`createTime`, `remark`) VALUES ('2015/12/28', '最后一周');
INSERT INTO `t_test_week` (`createTime`, `remark`) VALUES ('2015/12/29', '最后一周');
INSERT INTO `t_test_week` (`createTime`, `remark`) VALUES ('2015/12/30', '最后一周');
INSERT INTO `t_test_week` (`createTime`, `remark`) VALUES ('2015/12/31', '最后一周');

INSERT INTO `t_test_week` (`createTime`, `remark`) VALUES ('2016/01/01', '第一周');
INSERT INTO `t_test_week` (`createTime`, `remark`) VALUES ('2016/01/02', '第一周');
INSERT INTO `t_test_week` (`createTime`, `remark`) VALUES ('2016/01/03', '第一周');
INSERT INTO `t_test_week` (`createTime`, `remark`) VALUES ('2016/01/04', '第二周');
INSERT INTO `t_test_week` (`createTime`, `remark`) VALUES ('2016/01/05', '第二周');
INSERT INTO `t_test_week` (`createTime`, `remark`) VALUES ('2016/01/06', '第二周');
INSERT INTO `t_test_week` (`createTime`, `remark`) VALUES ('2016/01/07', '第二周');
INSERT INTO `t_test_week` (`createTime`, `remark`) VALUES ('2016/01/08', '第二周');
INSERT INTO `t_test_week` (`createTime`, `remark`) VALUES ('2016/01/09', '第二周');
INSERT INTO `t_test_week` (`createTime`, `remark`) VALUES ('2016/01/10', '第二周');
INSERT INTO `t_test_week` (`createTime`, `remark`) VALUES ('2016/01/11', '第三周');


SELECT
  DATE_FORMAT(`createTime`, '%Y%u') weeks,
  count(*),
  min(`createTime`)                 minTime
FROM `t_test_week`
GROUP BY weeks;

-- 模式7才符合我们的预期
SELECT
  WEEK(`createTime`, 7) weeks,
  count(*),
  min(`createTime`)     minTime
FROM `t_test_week`
GROUP BY weeks
ORDER BY minTime;

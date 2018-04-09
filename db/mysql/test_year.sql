-- 用来测试根据月份统计的能力
DROP TABLE IF EXISTS `t_test_year`;

CREATE TABLE `t_test_year` (
  `createTime` DATETIME NOT NULL,
  `remark`     VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`createTime`)
);

INSERT INTO `t_test_year` (`createTime`, `remark`) VALUES ('2016/01/01', '一月');
INSERT INTO `t_test_year` (`createTime`, `remark`) VALUES ('2016/02/01', '二月');
INSERT INTO `t_test_year` (`createTime`, `remark`) VALUES ('2016/03/01', '三月');
INSERT INTO `t_test_year` (`createTime`, `remark`) VALUES ('2016/04/01', '四月');
INSERT INTO `t_test_year` (`createTime`, `remark`) VALUES ('2016/05/01', '五月');
INSERT INTO `t_test_year` (`createTime`, `remark`) VALUES ('2016/06/01', '六月');
INSERT INTO `t_test_year` (`createTime`, `remark`) VALUES ('2016/07/01', '七月');
INSERT INTO `t_test_year` (`createTime`, `remark`) VALUES ('2016/08/01', '八月');
INSERT INTO `t_test_year` (`createTime`, `remark`) VALUES ('2016/09/01', '九月');
INSERT INTO `t_test_year` (`createTime`, `remark`) VALUES ('2016/10/01', '十月');
INSERT INTO `t_test_year` (`createTime`, `remark`) VALUES ('2016/11/01', '十一月');
INSERT INTO `t_test_year` (`createTime`, `remark`) VALUES ('2016/12/01', '十二月');

-- 开始测试
SELECT
  DATE_FORMAT(`createTime`, '%Y%m') months,
  count(*)                          count
FROM `t_test_year`
GROUP BY months;

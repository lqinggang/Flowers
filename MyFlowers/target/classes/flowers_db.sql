/*
	MySQL Workbench 6.3 CE

	Source Server         : mysql
	Source Server Version : 50713
	Source Host           : localhost:3306
	Source Database       : flowers_db

	Target Server Type    : MYSQL
	Target Server Version : 50713
	File Encoding         : 65001

	Date: 2018-03-22 10:24:08
*/
-- ---------------------------------------------------------------------------------------------

USE `flowers_db`;

-- ------------------------------
-- 取消外键约束
-- ------------------------------
SET FOREIGN_KEY_CHECKS=0;

-- ---------------------------------------------------------------------------------------------
-- ----------------------------
-- 鲜花类别表
-- ----------------------------
DROP TABLE IF EXISTS `flowers_category`;
CREATE TABLE `flowers_category` (
  `category_id` TINYINT(4) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '鲜花类型ID',
  `type_name` VARCHAR(20) DEFAULT 'NULL' COMMENT '鲜花类型名称',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 鲜花类别表记录
-- ----------------------------

INSERT INTO `flowers_category`(`category_id`,`type_name`) VALUES (1,'节日鲜花');
INSERT INTO `flowers_category`(`category_id`,`type_name`) VALUES (2,'婚礼鲜花');
INSERT INTO `flowers_category`(`category_id`,`type_name`) VALUES (3,'爱情鲜花');
INSERT INTO `flowers_category`(`category_id`,`type_name`) VALUES (4,'探望慰问');
INSERT INTO `flowers_category`(`category_id`,`type_name`) VALUES (5,'问候长辈');
INSERT INTO `flowers_category`(`category_id`,`type_name`) VALUES (6,'其他鲜花');

-- ---------------------------------------------------------------------------------------------

-- ----------------------------
-- 鲜花表
-- ----------------------------
DROP TABLE IF EXISTS `flowers`;
CREATE TABLE `flowers`(
	`flower_id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '鲜花ID',
    `name` VARCHAR(50) DEFAULT NULL COMMENT '鲜花名称',
    `keyword` VARCHAR(50) DEFAULT 'NULL' COMMENT '搜索关键字',
    `color` VARCHAR(15) DEFAULT 'NULL' COMMENT '鲜花颜色',
 	`amount` INT(5) DEFAULT '1' COMMENT '枝数',
    `origin` VARCHAR(64)  DEFAULT '未知' COMMENT '产地',
	`image` VARCHAR(255) DEFAULT 'static/pages/images/flowers/festival/juhua_1.jpg' COMMENT '鲜花图片URL',
    `quantity` INT(10) UNSIGNED DEFAULT '0' COMMENT '鲜花库存量',
    `price` FLOAT(5) UNSIGNED DEFAULT '0' COMMENT '鲜花价格',
	`category_id` TINYINT(4) UNSIGNED NOT NULL COMMENT '鲜花类型ID',
    `description` VARCHAR(255) DEFAULT '无' COMMENT '鲜花花语',
    `content` VARCHAR(1024) DEFAULT '暂无商品内容' COMMENT '商品内容',
	PRIMARY KEY (`flower_id`),
	KEY `flowers_category`(`category_id`),
    CONSTRAINT `flowers_category_id` FOREIGN KEY (`category_id`) REFERENCES `flowers_category` (`category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8;

-- ----------------------------
-- 鲜花表记录
-- ----------------------------
-- 节日鲜花
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('相思-缅怀','菊花','黄色',7,'北京','static/pages/images/flowers/festival/juhua_1.jpg',214,4.99,1,'清净、高洁、怀念、成功','static/pages/images/flowers/festival/juhua_1.jpg,static/pages/images/flowers/festival/juhua_1.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('相思-追忆','菊花','黄色',5,'桂林','static/pages/images/flowers/festival/juhua_2.jpg',299,9.99,1,'追想、可靠的爱情、请相信我','static/pages/images/flowers/festival/juhua_2.jpg,static/pages/images/flowers/festival/juhua_2.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('康乃馨·母爱','康乃馨','紫色',1,'桂林','static/pages/images/flowers/festival/kangnaixin_1.jpg',124,9.99,1,'爱,魅力,尊敬之情','static/pages/images/flowers/festival/kangnaixin_1.jpg,static/pages/images/flowers/festival/kangnaixin_1.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('康乃馨·感恩','康乃馨','粉色',3,'桂林','static/pages/images/flowers/festival/kangnaixin_2.jpg',324,9.99,1,'爱,魅力,尊敬之情','static/pages/images/flowers/festival/kangnaixin_2.jpg,static/pages/images/flowers/festival/kangnaixin_2.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('康乃馨·馨情无限','康乃馨','橙色',5,'桂林','static/pages/images/flowers/festival/kangnaixin_3.jpg',418,9.99,1,'爱,魅力,尊敬之情','static/pages/images/flowers/festival/kangnaixin_3.jpg,static/pages/images/flowers/festival/kangnaixin_3.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('康乃馨·暖暖的问候','康乃馨','白色',1,'桂林','static/pages/images/flowers/festival/kangnaixin_4.jpg',322,9.99,1,'爱,魅力,尊敬之情','static/pages/images/flowers/festival/kangnaixin_4.jpg,static/pages/images/flowers/festival/kangnaixin_4.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('香石竹·热情','香石竹','红色',7,'桂林','static/pages/images/flowers/festival/xiangshizhu_1.jpg',93,6.99,1,'热情、魅力、使人柔弱的爱、真情、母亲我爱你','static/pages/images/flowers/festival/xiangshizhu_1.jpg,static/pages/images/flowers/festival/xiangshizhu_1.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('香石竹·母爱','香石竹','紫色',12,'桂林','static/pages/images/flowers/festival/xiangshizhu_2.jpg',103,8.99,1,'热情、魅力、使人柔弱的爱、真情、母亲我爱你','static/pages/images/flowers/festival/xiangshizhu_2.jpg,static/pages/images/flowers/festival/xiangshizhu_2.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('我心在燃烧','一品红','红色',12,'桂林','static/pages/images/flowers/festival/yipinhong_1.jpg',423,6.95,1,'绘出你一片炽热的热情、我的心正在燃烧','static/pages/images/flowers/festival/yipinhong_1.jpg,static/pages/images/flowers/festival/yipinhong_1.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('绘出你炽热的热情','一品红','红色',12,'桂林','static/pages/images/flowers/festival/yipinhong_2.jpg',413,6.99,1,'绘出你一片炽热的热情、我的心正在燃烧','static/pages/images/flowers/festival/yipinhong_2.jpg,static/pages/images/flowers/festival/yipinhong_2.jpg');

-- 婚礼鲜花
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('迎新纳福','蝴蝶兰','粉色',3,'桂林','static/pages/images/flowers/wedding/hudielan_1.jpg',344,4.99,2,'高洁、大福大贵、清雅、我爱你','static/pages/images/flowers/wedding/hudielan_1.jpg,static/pages/images/flowers/wedding/hudielan_1.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('吉祥如意','蝴蝶兰','紫色',1,'桂林','static/pages/images/flowers/wedding/hudielan_2.jpg',729,9.99,2,'高洁、大福大贵、清雅、我爱你','static/pages/images/flowers/wedding/hudielan_2.jpg,static/pages/images/flowers/wedding/hudielan_2.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('十全十美','蝴蝶兰','红色',3,'桂林','static/pages/images/flowers/wedding/hudielan_3.jpg',134,12.99,2,'高洁、大福大贵、清雅、我爱你','static/pages/images/flowers/wedding/hudielan_3.jpg,static/pages/images/flowers/wedding/hudielan_3.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('大福大贵','蝴蝶兰','蓝色',3,'桂林','static/pages/images/flowers/wedding/hudielan_4.jpg',134,9.99,2,'高洁、大福大贵、清雅、我爱你','static/pages/images/flowers/wedding/hudielan_4.jpg,static/pages/images/flowers/wedding/hudielan_4.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('永结同心','马蹄莲','白色',5,'桂林','static/pages/images/flowers/wedding/matilian_1.jpg',818,9.99,2,'幸福、纯洁，象征“圣法虔诚，永结同心，吉祥如意。”','static/pages/images/flowers/wedding/matilian_1.jpg,static/pages/images/flowers/wedding/matilian_1.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('清晨的问候','马蹄莲','黄色',1,'桂林','static/pages/images/flowers/wedding/matilian_2.jpg',93,6.99,2,'幸福、纯洁，象征“圣法虔诚，永结同心，吉祥如意。”','static/pages/images/flowers/wedding/matilian_2.jpg,static/pages/images/flowers/wedding/matilian_2.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('吉祥如意','马蹄莲','黄色',12,'桂林','static/pages/images/flowers/wedding/matilian_3.jpg',103,8.99,2,'幸福、纯洁，象征“圣法虔诚，永结同心，吉祥如意。”','static/pages/images/flowers/wedding/matilian_3.jpg,static/pages/images/flowers/wedding/matilian_3.jpg');


-- 爱情鲜花
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('玫瑰·爱的宣言','玫瑰','粉色',1,'桂林','static/pages/images/flowers/love/meigui_1.jpg',644,14.99,3,'感动、爱的宣言、铭记于心 、初恋，喜欢你那灿烂的笑容。','static/pages/images/flowers/love/meigui_1.jpg,static/pages/images/flowers/love/meigui_1.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('玫瑰·歉意','玫瑰','黄色',3,'桂林','static/pages/images/flowers/love/meigui_2.jpg',729,19.99,3,'不贞、嫉妒,　欢乐, 高兴, 道歉','static/pages/images/flowers/love/meigui_2.jpg,static/pages/images/flowers/love/meigui_2.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('玫瑰·初恋','玫瑰','粉色',3,'桂林','static/pages/images/flowers/love/meigui_3.jpg',834,21.99,3,'感动、爱的宣言、铭记于心 、初恋，喜欢你那灿烂的笑容。','static/pages/images/flowers/love/meigui_3.jpg,static/pages/images/flowers/love/meigui_3.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('玫瑰·热爱','玫瑰','黄色',12,'桂林','static/pages/images/flowers/love/meigui_4.jpg',934,19.99,3,'不贞、嫉妒,　欢乐, 高兴, 道歉','static/pages/images/flowers/love/meigui_4.jpg,static/pages/images/flowers/love/meigui_4.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('玫瑰·神秘的爱','玫瑰','黄色',3,'桂林','static/pages/images/flowers/love/meigui_5.jpg',1018,19.99,3,'不贞、嫉妒,　欢乐, 高兴, 道歉','static/pages/images/flowers/love/meigui_5.jpg,static/pages/images/flowers/love/meigui_5.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('玫瑰·青春','玫瑰','橙色',3,'桂林','static/pages/images/flowers/love/meigui_6.jpg',693,16.99,3,'献给你一份神秘的爱;富有青春气息、初恋的心情。','static/pages/images/flowers/love/meigui_6.jpg,static/pages/images/flowers/love/meigui_6.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('玫瑰·铭记','玫瑰','粉色',7,'桂林','static/pages/images/flowers/love/meigui_7.jpg',1103,18.99,3,'感动、爱的宣言、铭记于心 、初恋，喜欢你那灿烂的笑容。','static/pages/images/flowers/love/meigui_7.jpg,static/pages/images/flowers/love/meigui_7.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('玫瑰·一心一意','玫瑰','粉色',1,'桂林','static/pages/images/flowers/love/meigui_8.jpg',943,16.99,3,'感动、爱的宣言、铭记于心 、初恋，喜欢你那灿烂的笑容。','static/pages/images/flowers/love/meigui_8.jpg,static/pages/images/flowers/love/meigui_8.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('玫瑰·不变的承诺','玫瑰','粉色',5,'桂林','static/pages/images/flowers/love/meigui_9.jpg',1043,18.99,3,'感动、爱的宣言、铭记于心 、初恋，喜欢你那灿烂的笑容。','static/pages/images/flowers/love/meigui_9.jpg,static/pages/images/flowers/love/meigui_9.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('玫瑰·一往情深','玫瑰','红色',3,'桂林','static/pages/images/flowers/love/meigui_10.jpg',644,14.99,3,'热爱着您　我爱你、热恋，希望与你泛起激情的爱','static/pages/images/flowers/love/meigui_10.jpg,static/pages/images/flowers/love/meigui_10.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('玫瑰·真爱如初','玫瑰','红色',7,'桂林','static/pages/images/flowers/love/meigui_11.jpg',524,19.99,3,'热爱着您　我爱你、热恋，希望与你泛起激情的爱','static/pages/images/flowers/love/meigui_11.jpg,static/pages/images/flowers/love/meigui_11.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('玫瑰·守望','玫瑰','蓝色',3,'桂林','static/pages/images/flowers/love/meigui_12.jpg',443,18.99,3,' 奇迹与不可能实现的事，希望挚爱的你幸福。','static/pages/images/flowers/love/meigui_12.jpg,static/pages/images/flowers/love/meigui_12.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('荷兰风情','郁金香','粉色',5,'桂林','static/pages/images/flowers/love/yujinxiang_1.jpg',644,14.95,3,'爱、慈善、名誉、美丽、祝福、永恒、爱的表白和永恒的祝福','static/pages/images/flowers/love/yujinxiang_1.jpg,static/pages/images/flowers/love/yujinxiang_1.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('爱的表白','郁金香','黄色',12,'桂林','static/pages/images/flowers/love/yujinxiang_2.jpg',729,19.95,3,'爱、慈善、名誉、美丽、祝福、永恒、爱的表白和永恒的祝福','static/pages/images/flowers/love/yujinxiang_2.jpg,static/pages/images/flowers/love/yujinxiang_2.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('永恒的祝福','郁金香','红色',12,'桂林','static/pages/images/flowers/love/yujinxiang_3.jpg',834,21.96,3,'爱、慈善、名誉、美丽、祝福、永恒、爱的表白和永恒的祝福','static/pages/images/flowers/love/yujinxiang_3.jpg,static/pages/images/flowers/love/yujinxiang_3.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('亭亭玉立','郁金香','黄色',12,'桂林','static/pages/images/flowers/love/yujinxiang_4.jpg',934,19.96,3,'爱、慈善、名誉、美丽、祝福、永恒、爱的表白和永恒的祝福','static/pages/images/flowers/love/yujinxiang_4.jpg,static/pages/images/flowers/love/yujinxiang_4.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('爱的芬芳','郁金香','紫色',3,'桂林','static/pages/images/flowers/love/yujinxiang_5.jpg',1018,19.96,3,'爱、慈善、名誉、美丽、祝福、永恒、爱的表白和永恒的祝福','static/pages/images/flowers/love/yujinxiang_5.jpg,static/pages/images/flowers/love/yujinxiang_5.jpg');

-- 探望慰问
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('牡丹·圆满','牡丹','紫色',3,'桂林','static/pages/images/flowers/condolence/mudan_1.jpg',644,14.99,4,'圆满、浓情、富贵','static/pages/images/flowers/condolence/mudan_1.jpg,static/pages/images/flowers/condolence/mudan_1.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('牡丹·浓情','牡丹','粉色',1,'桂林','static/pages/images/flowers/condolence/mudan_2.jpg',729,19.99,4,'圆满、浓情、富贵','static/pages/images/flowers/condolence/mudan_2.jpg,static/pages/images/flowers/condolence/mudan_2.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('牡丹·富贵','牡丹','紫色',1,'桂林','static/pages/images/flowers/condolence/mudan_3.jpg',834,21.99,4,'圆满、浓情、富贵','static/pages/images/flowers/condolence/mudan_3.jpg,static/pages/images/flowers/condolence/mudan_3.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('月季·希望','月季','粉色',1,'桂林','static/pages/images/flowers/condolence/yueji_1.jpg',934,19.99,4,'等待有希望的希望,幸福、光荣','static/pages/images/flowers/condolence/yueji_1.jpg,static/pages/images/flowers/condolence/yueji_1.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('月季·尊敬','月季','白色',1,'桂林','static/pages/images/flowers/condolence/yueji_2.jpg',1018,19.99,4,'等待有希望的希望,幸福、光荣','static/pages/images/flowers/condolence/yueji_2.jpg,static/pages/images/flowers/condolence/yueji_2.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('月季·崇高','月季','黄色',1,'桂林','static/pages/images/flowers/condolence/yueji_3.jpg',693,16.99,4,'等待有希望的希望,幸福、光荣','static/pages/images/flowers/condolence/yueji_3.jpg,static/pages/images/flowers/condolence/yueji_3.jpg');


-- 问候长辈
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('百年好合','百合','粉色',3,'桂林','static/pages/images/flowers/elder/baihe_1.jpg',113,18.99,5,'百年好合、美好家庭、伟大的爱，深深祝福','static/pages/images/flowers/elder/baihe_1.jpg,static/pages/images/flowers/elder/baihe_1.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('美好家庭','百合','白色',3,'桂林','static/pages/images/flowers/elder/baihe_2.jpg',943,16.99,5,'百年好合、美好家庭、伟大的爱，深深祝福','static/pages/images/flowers/elder/baihe_2.jpg,static/pages/images/flowers/elder/baihe_2.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('伟大的爱','百合','白色',1,'桂林','static/pages/images/flowers/elder/baihe_3.jpg',1043,18.99,5,'百年好合、美好家庭、伟大的爱，深深祝福','static/pages/images/flowers/elder/baihe_3.jpg,static/pages/images/flowers/elder/baihe_3.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('深深祝福','百合','白色',1,'桂林','static/pages/images/flowers/elder/baihe_4.jpg',644,14.95,5,'百年好合、美好家庭、伟大的爱，深深祝福','static/pages/images/flowers/elder/baihe_4.jpg,static/pages/images/flowers/elder/baihe_4.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('百合·平静','百合','粉色',7,'桂林','static/pages/images/flowers/elder/baihe_5.jpg',729,19.95,5,'百年好合、美好家庭、伟大的爱，深深祝福','static/pages/images/flowers/elder/baihe_5.jpg,static/pages/images/flowers/elder/baihe_5.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('寿比南山','长寿花','红色',7,'桂林','static/pages/images/flowers/elder/changshou_1.jpg',334,21.96,5,'大吉大利、长命百岁、福寿吉庆','static/pages/images/flowers/elder/changshou_1.jpg,static/pages/images/flowers/elder/changshou_1.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('长命百岁','长寿花','紫色',3,'桂林','static/pages/images/flowers/elder/changshou_2.jpg',234,19.96,5,'大吉大利、长命百岁、福寿吉庆','static/pages/images/flowers/elder/changshou_2.jpg,static/pages/images/flowers/elder/changshou_2.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('福寿吉庆','长寿花','红色',12,'桂林','static/pages/images/flowers/elder/changshou_3.jpg',118,19.96,5,'大吉大利、长命百岁、福寿吉庆','static/pages/images/flowers/elder/changshou_3.jpg,static/pages/images/flowers/elder/changshou_3.jpg');


-- 其他鲜花
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('高风亮节','荷花','粉色',1,'桂林','static/pages/images/flowers/other/hehua_1.jpg',644,4.95,6,'清白、坚贞和纯洁','static/pages/images/flowers/other/hehua_1.jpg,static/pages/images/flowers/other/hehua_1.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('清正','荷花','粉色',1,'桂林','static/pages/images/flowers/other/hehua_2.jpg',729,5.95,6,'清白、坚贞和纯洁','static/pages/images/flowers/other/hehua_2.jpg,static/pages/images/flowers/other/hehua_2.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('隐藏的爱','兰花','白色',7,'桂林','static/pages/images/flowers/other/lanhua_1.jpg',334,21.96,6,'美好、高洁、贤德','static/pages/images/flowers/other/lanhua_1.jpg,static/pages/images/flowers/other/lanhua_1.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('淡泊','兰花','紫色',3,'桂林','static/pages/images/flowers/other/lanhua_2.jpg',234,19.96,6,'美好、高洁、贤德','static/pages/images/flowers/other/lanhua_2.jpg,static/pages/images/flowers/other/lanhua_2.jpg');
INSERT INTO `flowers`(`name`,`keyword`,`color`,`amount`,`origin`,`image`,`quantity`,`price`,`category_id`,`description`,`content`) VALUES('贤德','兰花','白色',3,'桂林','static/pages/images/flowers/other/lanhua_3.jpg',118,19.96,6,'美好、高洁、贤德','static/pages/images/flowers/other/lanhua_3.jpg,static/pages/images/flowers/other/lanhua_3.jpg');



-- ----------------------------
-- person表
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person`(
	`person_id` INT(25) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `name` VARCHAR(50) DEFAULT 'name' COMMENT '名称',
	`password` VARCHAR(255) DEFAULT '5F4DCC3B5AA765D61D8327DEB882CF99' COMMENT '密码',
	UNIQUE KEY Person_Name(name),
	PRIMARY KEY(`person_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8;

-- ----------------------------
-- person表记录
-- ----------------------------
INSERT INTO `person`(`person_id`,`name`,`password`) VALUES('1','username','5F4DCC3B5AA765D61D8327DEB882CF99');
INSERT INTO `person`(`person_id`,`name`,`password`) VALUES('2','test','5F4DCC3B5AA765D61D8327DEB882CF99');
INSERT INTO `person`(`person_id`,`name`,`password`) VALUES('3','flowers','5F4DCC3B5AA765D61D8327DEB882CF99');
INSERT INTO `person`(`person_id`,`name`,`password`) VALUES('4','name','5F4DCC3B5AA765D61D8327DEB882CF99');
-- admin
INSERT INTO `person`(`person_id`,`name`,`password`) VALUES('5','lqinggang','5F4DCC3B5AA765D61D8327DEB882CF99');
INSERT INTO `person`(`person_id`,`name`,`password`) VALUES('6','admin','5F4DCC3B5AA765D61D8327DEB882CF99');
-- ---------------------------------------------------------------------------------------------


-- ----------------------------
-- 联系方式表
-- ----------------------------
DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact`(
	`person_id`  INT(25) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `telephone` VARCHAR(20) DEFAULT '00000000000' COMMENT '电话号码',
	`email` VARCHAR(255) DEFAULT 'email@email.com' COMMENT '电子邮件',
    `address` VARCHAR(255) DEFAULT '未知' COMMENT '地址',
    CONSTRAINT `contact_person_id` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

-- ----------------------------
-- 联系方式表记录
-- ----------------------------
INSERT INTO `contact`(`person_id`, `telephone`,`email`,`address`) VALUES('1','18383947602','username@email.com','110000,110100,110101,这是username的地址');
INSERT INTO `contact`(`person_id`, `telephone`,`email`,`address`) VALUES('2','13309583725','test@email.com','110000,110100,110101,这是test的地址');
INSERT INTO `contact`(`person_id`, `telephone`,`email`,`address`) VALUES('3','13759159824','flowers@email.com','110000,110100,110101,这是flowers的地址');
INSERT INTO `contact`(`person_id`, `telephone`,`email`,`address`) VALUES('4','15232844892','name@email.com','110000,110100,110101,这是name的地址');

INSERT INTO `contact`(`person_id`, `telephone`,`email`,`address`) VALUES('5','17742942394','lqinggang@email.com','110000,110100,110101,这是lqinggang的地址');
INSERT INTO `contact`(`person_id`, `telephone`,`email`,`address`) VALUES('6','19423402402','admin@email.com','110000,110100,110101,这是admin的地址');
-- ---------------------------------------------------------------------------------------------

-- ----------------------------
-- 用户表
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`(
	`person_id`  INT(25) UNSIGNED NOT NULL AUTO_INCREMENT   COMMENT '用户ID',
--     `portrait` VARCHAR(255) DEFAULT NULL COMMENT '用户头像',
    `gender` VARCHAR(2) DEFAULT '女' COMMENT '用户性别',
    `age` TINYINT(4) DEFAULT '0' COMMENT '用户年龄',
    `birtyday` DATE DEFAULT '1995-08-05' COMMENT '用户生日',
    `register_date` DATETIME  DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间', 
	`description` VARCHAR(255) DEFAULT NULL COMMENT '用户备注',
    CONSTRAINT `users_person_id` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

-- ----------------------------
-- 用户表记录
-- ----------------------------
INSERT INTO `users`(`person_id`,`gender`,`age`,`birtyday`,`register_date`,`description`) VALUES('1','男','23','1995-08-05','2018-04-04 09:48:28','这里是用户username的备注');
INSERT INTO `users`(`person_id`,`gender`,`age`,`birtyday`,`register_date`,`description`) VALUES('2','男','33','1985-02-11','2018-04-04 09:48:28','这里是用户test的备注');
INSERT INTO `users`(`person_id`,`gender`,`age`,`birtyday`,`register_date`,`description`) VALUES('3','女','21','1997-11-09','2018-04-04 09:48:28','这里是用户flowers的备注');
INSERT INTO `users`(`person_id`,`gender`,`age`,`birtyday`,`register_date`,`description`) VALUES('4','女','30','1989-05-04','2018-04-04 09:48:28','这里是用户name的备注');
-- ---------------------------------------------------------------------------------------------


-- ----------------------------
-- 会员表
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`(
	`person_id`  INT(25) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',
	`rank` TINYINT(3) DEFAULT '0' COMMENT '用户等级',
    `experience` INT(8) DEFAULT '0' COMMENT '用户经验',
    `discount` FLOAT UNSIGNED DEFAULT '1' COMMENT '折率',
    CONSTRAINT `member_person_id` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

-- ----------------------------
-- 会员表记录
-- ----------------------------
INSERT INTO `member`(`person_id`,`rank`,`experience`,`discount`) VALUES('1','1','42','1');
INSERT INTO `member`(`person_id`,`rank`,`experience`,`discount`) VALUES('2','3','2042','0.8');
INSERT INTO `member`(`person_id`,`rank`,`experience`,`discount`) VALUES('3','2','1442','0.9');
INSERT INTO `member`(`person_id`,`rank`,`experience`,`discount`) VALUES('4','3','2242','0.8');

-- ---------------------------------------------------------------------------------------------

-- ----------------------------
-- 管理员表
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`(
	`person_id` INT(25) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',
   /* `permission` TINYINT(3) COMMENT '权限',*/
    `last_login` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '最后登录时间',
     CONSTRAINT `admin_person_id` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

-- ----------------------------
-- 管理员表记录
-- ----------------------------
INSERT INTO `admin`(`person_id`,`last_login`) VALUES('5','2018-04-04 09:48:28');
INSERT INTO `admin`(`person_id`,`last_login`) VALUES('6','2018-04-04 09:48:28');
-- ---------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS `logs`;
CREATE TABLE `logs`(	
	`logs_id` INT(25) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '日志ID',
	`person_id` INT(25) UNSIGNED NOT NULL  COMMENT '用户ID',
	`ipaddress` VARCHAR(64) DEFAULT '127.0.0.1' COMMENT '登陆IP',
    `date` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT  '时间',
	`content` VARCHAR(255) DEFAULT 'NULL' COMMENT '日志内容',
    PRIMARY KEY(`logs_id`),
    CONSTRAINT `admin_logs_person_id` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`) ON DELETE CASCADE ON UPDATE NO ACTION
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

INSERT INTO `logs`(`logs_id`,`person_id`,`ipaddress`,`date`,`content`) VALUES('1','5','125.217.58.64','2018-05-02 10:34:50.0','lqinggang登陆');
INSERT INTO `logs`(`logs_id`,`person_id`,`ipaddress`,`date`,`content`) VALUES('2','5','125.217.58.64','2018-05-02 10:35:50.0','lqinggang登陆');
INSERT INTO `logs`(`logs_id`,`person_id`,`ipaddress`,`date`,`content`) VALUES('3','5','125.217.58.64','2018-05-02 10:36:50.0','lqinggang登陆');
INSERT INTO `logs`(`logs_id`,`person_id`,`ipaddress`,`date`,`content`) VALUES('4','5','125.217.58.64','2018-05-02 10:37:50.0','lqinggang删除鲜花信息');
INSERT INTO `logs`(`logs_id`,`person_id`,`ipaddress`,`date`,`content`) VALUES('5','5','125.217.58.64','2018-05-02 10:38:50.0','lqinggang删除鲜花信息');
INSERT INTO `logs`(`logs_id`,`person_id`,`ipaddress`,`date`,`content`) VALUES('6','5','125.217.58.64','2018-05-02 10:39:50.0','lqinggang登陆');
INSERT INTO `logs`(`logs_id`,`person_id`,`ipaddress`,`date`,`content`) VALUES('7','5','125.217.58.64','2018-05-02 10:40:50.0','lqinggang新增用户2信息');
INSERT INTO `logs`(`logs_id`,`person_id`,`ipaddress`,`date`,`content`) VALUES('8','5','125.217.58.64','2018-05-02 10:41:50.0','lqinggang删除用户2信息');


-- ----------------------------
-- sales表
-- ----------------------------
/*
DROP TABLE IF EXISTS `sales`;
CREATE TABLE `sales`(
	`flower_id` INT(11) UNSIGNED NOT NULL  COMMENT '鲜花ID',
	`person_id`  INT(25) UNSIGNED NOT NULL  COMMENT '用户ID',
    `date` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '销售时间',
    `amount` INT(10) UNSIGNED DEFAULT '0' COMMENT '销售量',
    CONSTRAINT `sales_flower_id` FOREIGN KEY (`flower_id`) REFERENCES `flowers` (`flower_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
	CONSTRAINT `sales_person_id` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8;

*/
-- ----------------------------
-- sales表记录
-- ----------------------------
-- INSERT INTO `sales` VALUES();

-- ---------------------------------------------------------------------------------------------

-- ----------------------------
-- 配送人员表
-- ----------------------------
/*
DROP TABLE IF EXISTS `distribution`;
CREATE TABLE `distribution`(
	`person_id`  INT(25) UNSIGNED NOT NULL  COMMENT '用户ID',
	`status` VARCHAR(5) DEFAULT '0' COMMENT '配送人员状态 0:闲 1:忙',
     CONSTRAINT `distribution_person_id` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
*/
-- ----------------------------
-- distribution表记录
-- ----------------------------
-- INSERT INTO `distribution` VALUES();

-- ---------------------------------------------------------------------------------------------

-- ---------------------------------------------------------------------------------------------

-- ----------------------------
-- 订单状态表
-- ----------------------------
DROP TABLE IF EXISTS `order_status_type`;
CREATE TABLE `order_status_type`(
	`status_type_id` INT(20) UNSIGNED NOT NULL COMMENT '订单当前状态',
    `status_name` VARCHAR(50) DEFAULT '订单状态' COMMENT '订单状态名称',
    PRIMARY KEY(`status_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

-- ----------------------------
-- 订单状态表记录
-- ----------------------------
INSERT INTO `order_status_type`(`status_type_id`,`status_name`) VALUES('0','已取消');
INSERT INTO `order_status_type`(`status_type_id`,`status_name`) VALUES('1','待发货');
INSERT INTO `order_status_type`(`status_type_id`,`status_name`) VALUES('2','待签收');
INSERT INTO `order_status_type`(`status_type_id`,`status_name`) VALUES('3','待评价');
INSERT INTO `order_status_type`(`status_type_id`,`status_name`) VALUES('4','已评价');

-- ---------------------------------------------------------------------------------------------


-- ----------------------------
-- 订单表
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`(
	`order_id` VARCHAR(20) NOT NULL COMMENT '订单编号',
    `status_type_id` INT(20) UNSIGNED NOT NULL COMMENT '订单当前状态',
	`flower_id` INT(11) UNSIGNED NOT NULL COMMENT '鲜花ID',
	`person_id`  INT(25) UNSIGNED DEFAULT NULL COMMENT '用户ID',
    `recipient` VARCHAR(64) NOT NULL COMMENT '收件人名字',
    `contact` VARCHAR(16) NOT NULL COMMENT '收件人联系方式',
    `address` VARCHAR(128) NOT NULL COMMENT '收件人地址',
    `date` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
    `price` FLOAT(5) NOT NULL COMMENT '订单总价',
    `amount` INT(5) NOT NULL COMMENT '鲜花购买量',
    `note` VARCHAR(255) DEFAULT 'NULL' COMMENT '订单备注',
    PRIMARY KEY(`order_id`),
    CONSTRAINT `orders_flower_id` FOREIGN KEY (`flower_id`) REFERENCES `flowers` (`flower_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
	CONSTRAINT `orders_status_type` FOREIGN KEY (`status_type_id`) REFERENCES `order_status_type` (`status_type_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
	CONSTRAINT `order_person_id` FOREIGN KEY (`person_id`) REFERENCES `person`(`person_id`) ON DELETE CASCADE ON UPDATE NO ACTION 
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

-- ----------------------------
-- 订单表记录
-- ----------------------------
INSERT INTO `orders`(`order_id`,`status_type_id`,`flower_id`,`person_id`,`recipient`, `contact`, `address`,`date`,`price`, `amount`,`note`) VALUES('2018041316103641098','4','51','1','收件人','18378398000','中国','2018-04-04 09:48:28','24.95','2','尽快送达！！');
INSERT INTO `orders`(`order_id`,`status_type_id`,`flower_id`,`person_id`,`recipient`, `contact`, `address`,`date`,`price`, `amount`,`note`) VALUES('2018041316103641099','1','1','1','收件人','18378398000','中国','2018-04-04 09:48:28','24.95','5','尽快送达！！');
INSERT INTO `orders`(`order_id`,`status_type_id`,`flower_id`,`person_id`,`recipient`, `contact`, `address`,`date`,`price`, `amount`,`note`) VALUES('2018041316103641090','1','3','1','收件人','18378398000','中国','2018-04-04 09:48:28','24.95','5','尽快送达！！');

INSERT INTO `orders`(`order_id`,`status_type_id`,`flower_id`,`person_id`,`recipient`, `contact`, `address`,`date`,`price`, `amount`,`note`) VALUES('2018041316103641091','1','1','2','收件人','18378398000','中国','2018-04-04 09:48:28','24.95','5','尽快送达！！');
INSERT INTO `orders`(`order_id`,`status_type_id`,`flower_id`,`person_id`,`recipient`, `contact`, `address`,`date`,`price`, `amount`,`note`) VALUES('2018041316103641092','4','19','2','收件人','18378398000','中国','2018-04-04 09:48:28','24.95','5','尽快送达！！');
INSERT INTO `orders`(`order_id`,`status_type_id`,`flower_id`,`person_id`,`recipient`, `contact`, `address`,`date`,`price`, `amount`,`note`) VALUES('2018041316103641093','3','10','2','收件人','18378398000','中国','2018-04-04 09:48:28','24.95','5','尽快送达！！');
INSERT INTO `orders`(`order_id`,`status_type_id`,`flower_id`,`person_id`,`recipient`, `contact`, `address`,`date`,`price`, `amount`,`note`) VALUES('2018041316103641094','2','54','2','收件人','18378398000','中国','2018-04-04 09:48:28','24.95','5','尽快送达！！');
INSERT INTO `orders`(`order_id`,`status_type_id`,`flower_id`,`person_id`,`recipient`, `contact`, `address`,`date`,`price`, `amount`,`note`) VALUES('2018041316103641096','4','9','2','收件人','18378398000','中国','2018-04-04 09:48:28','24.95','5','尽快送达！！');
INSERT INTO `orders`(`order_id`,`status_type_id`,`flower_id`,`person_id`,`recipient`, `contact`, `address`,`date`,`price`, `amount`,`note`) VALUES('2018041416103641091','0','13','2','收件人','18378398000','中国','2018-04-04 09:48:28','24.95','5','尽快送达！！');
INSERT INTO `orders`(`order_id`,`status_type_id`,`flower_id`,`person_id`,`recipient`, `contact`, `address`,`date`,`price`, `amount`,`note`) VALUES('2018041416103641092','4','9','2','收件人','18378398000','中国','2018-04-04 09:48:28','24.95','5','尽快送达！！');

INSERT INTO `orders`(`order_id`,`status_type_id`,`flower_id`,`person_id`,`recipient`, `contact`, `address`,`date`,`price`, `amount`,`note`) VALUES('2018041416103641097','0','8','3','收件人','18378398000','中国','2018-04-04 09:48:28','24.95','5','尽快送达！！');
INSERT INTO `orders`(`order_id`,`status_type_id`,`flower_id`,`person_id`,`recipient`, `contact`, `address`,`date`,`price`, `amount`,`note`) VALUES('2018041416103641088','3','4','3','收件人','18378398000','中国','2018-04-04 09:48:28','24.95','5','快！！');
INSERT INTO `orders`(`order_id`,`status_type_id`,`flower_id`,`person_id`,`recipient`, `contact`, `address`,`date`,`price`, `amount`,`note`) VALUES('2018041416103641080','1','21','3','收件人','18378398000','中国','2018-04-04 09:48:28','24.95','5','尽快送达！！');
INSERT INTO `orders`(`order_id`,`status_type_id`,`flower_id`,`person_id`,`recipient`, `contact`, `address`,`date`,`price`, `amount`,`note`) VALUES('2018041316103641097','0','37','3','收件人','18378398000','中国','2018-04-04 09:48:28','24.95','5','尽快送达！！');
INSERT INTO `orders`(`order_id`,`status_type_id`,`flower_id`,`person_id`,`recipient`, `contact`, `address`,`date`,`price`, `amount`,`note`) VALUES('2018041316103641088','3','5','3','收件人','18378398000','中国','2018-04-04 09:48:28','24.95','5','快！！');
INSERT INTO `orders`(`order_id`,`status_type_id`,`flower_id`,`person_id`,`recipient`, `contact`, `address`,`date`,`price`, `amount`,`note`) VALUES('2018041316103641009','2','43','3','收件人','18378398000','中国','2018-04-04 09:48:28','24.95','5','尽快送达！！');
INSERT INTO `orders`(`order_id`,`status_type_id`,`flower_id`,`person_id`,`recipient`, `contact`, `address`,`date`,`price`, `amount`,`note`) VALUES('2018041316103641080','4','22','3','收件人','18378398000','中国','2018-04-04 09:48:28','24.95','5','尽快送达！！');



-- ----------------------------
-- orders_status表
-- ----------------------------
/*DROP TABLE IF EXISTS `orders_status`;
CREATE TABLE `orders_status`(
	`order_id` VARCHAR(20) NOT NULL COMMENT '订单编号',
    `cancel_status` BOOLEAN DEFAULT FALSE COMMENT '取消订单状态 FALSE:正常,TRUE:取消',
    `cancel_date` DATETIME COMMENT '取消订单时间',
    `cancel_renson` VARCHAR(255) DEFAULT NULL COMMENT '取消订单原因',
    `sign_status` BOOLEAN DEFAULT FALSE COMMENT '订单签收状态 TRUE:已签收,FALSE:未签收',
    `sign_date` DATETIME COMMENT '签收时间',
    `purchase_status` BOOLEAN DEFAULT FALSE COMMENT '订单评价状态 TRUE:已评价,FALSE:未评价',
    `purchase_date` DATETIME COMMENT '订单评价时间'
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
*/
-- ----------------------------
-- 订单状态内容表
-- ----------------------------
DROP TABLE IF EXISTS `orders_cancel`;
CREATE TABLE `orders_cancel`(
	`order_id` VARCHAR(20) NOT NULL COMMENT '订单编号',
--  `status` BOOLEAN DEFAULT '1' COMMENT '订单状态', -- 待发货,待签收,待评价:该订单不存在对应的记录，已取消：1;已评价:1; ???
	`content` VARCHAR(255) DEFAULT 'NULL' COMMENT '订单取消原因', -- 取消原因或评价内容
    `date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '订单取消时间',
     CONSTRAINT `orders_status_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

-- ----------------------------
-- 订单状态内容表记录
-- ----------------------------
INSERT INTO `orders_cancel`(`order_id`,`content`,`date`) VALUES('2018041416103641091','不需要了','2018-04-04 09:48:28');
INSERT INTO `orders_cancel`(`order_id`,`content`,`date`) VALUES('2018041416103641097','不好看','2018-04-04 09:48:28');
INSERT INTO `orders_cancel`(`order_id`,`content`,`date`) VALUES('2018041316103641097','在外,不方便','2018-04-04 09:48:28');

-- -------------------logs--------------------------------------------------------------------------

-- ----------------------------
-- 商品评价表记录
-- ----------------------------
DROP TABLE IF EXISTS `orders_purchase`;
CREATE TABLE `orders_purchase`(
	`order_id` VARCHAR(20) NOT NULL COMMENT '订单编号',
	`logistics` TINYINT(5) DEFAULT '5' COMMENT '物流评价',
    `commodity` TINYINT(5) DEFAULT '5' COMMENT '商品评价',
    `service` TINYINT(5) DEFAULT '5' COMMENT '服务评价',
    `date` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '评价时间',
    `purchase_content` VARCHAR(255) DEFAULT '此用户没有评价' COMMENT '评价内容',
	CONSTRAINT `orders_purchase_orders_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

-- ----------------------------
-- 商品评价表记录
-- ----------------------------
INSERT INTO `orders_purchase`(`order_id`,`logistics`,`commodity`,`service`,`purchase_content`,`date`) VALUES('2018041316103641098','5','5','5','不错,很好看！！','2018-04-04 09:48:28');
INSERT INTO `orders_purchase`(`order_id`,`logistics`,`commodity`,`service`,`purchase_content`,`date`) VALUES('2018041316103641092','3','5','5','不错,很好看！！','2018-04-04 09:48:28');
INSERT INTO `orders_purchase`(`order_id`,`logistics`,`commodity`,`service`,`purchase_content`,`date`) VALUES('2018041316103641096','5','4','5','不错,很好看！！','2018-04-04 09:48:28');
INSERT INTO `orders_purchase`(`order_id`,`logistics`,`commodity`,`service`,`purchase_content`,`date`) VALUES('2018041416103641092','5','3','5','不错,很好看！！','2018-04-04 09:48:28');
INSERT INTO `orders_purchase`(`order_id`,`logistics`,`commodity`,`service`,`purchase_content`,`date`) VALUES('2018041316103641080','1','2','3','差评!!!','2018-04-04 09:48:28');

-- ----------------------------
-- 用户购物车表
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`(
	`cart_id` INT(25) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '购物车ID',
	`person_id`  INT(25) UNSIGNED NOT NULL COMMENT '用户ID',
	`flower_id` INT(11) UNSIGNED NOT NULL  COMMENT '鲜花ID',
    `date` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '加入购物车时间',
    `amount` INT(5) DEFAULT '1' COMMENT '数量',
    PRIMARY KEY(`cart_id`),
	CONSTRAINT `cart_person_id` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT `cart_flower_id` FOREIGN KEY (`flower_id`) REFERENCES `flowers` (`flower_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1  DEFAULT CHARSET=UTF8;

-- ----------------------------
-- 用户购物车表记录
-- ----------------------------
INSERT INTO `cart`(`person_id`,`flower_id`,`date`,`amount`) VALUES('1','32','2018-04-04 09:48:28','6');
INSERT INTO `cart`(`person_id`,`flower_id`,`date`,`amount`) VALUES('1','36','2018-04-04 09:48:28','5');
INSERT INTO `cart`(`person_id`,`flower_id`,`date`,`amount`) VALUES('1','2','2018-04-04 09:48:28','3');
INSERT INTO `cart`(`person_id`,`flower_id`,`date`,`amount`) VALUES('1','22','2018-04-04 09:48:28','4');
INSERT INTO `cart`(`person_id`,`flower_id`,`date`,`amount`) VALUES('1','15','2018-04-04 09:48:28','1');

INSERT INTO `cart`(`person_id`,`flower_id`,`date`,`amount`) VALUES('2','42','2018-04-04 09:48:28','6');
INSERT INTO `cart`(`person_id`,`flower_id`,`date`,`amount`) VALUES('2','6','2018-04-04 09:48:28','5');
INSERT INTO `cart`(`person_id`,`flower_id`,`date`,`amount`) VALUES('2','29','2018-04-04 09:48:28','3');


INSERT INTO `cart`(`person_id`,`flower_id`,`date`,`amount`) VALUES('3','32','2018-04-04 09:48:28','6');
INSERT INTO `cart`(`person_id`,`flower_id`,`date`,`amount`) VALUES('3','36','2018-04-04 09:48:28','5');
INSERT INTO `cart`(`person_id`,`flower_id`,`date`,`amount`) VALUES('3','3','2018-04-04 09:48:28','3');
INSERT INTO `cart`(`person_id`,`flower_id`,`date`,`amount`) VALUES('3','22','2018-04-04 09:48:28','4');
INSERT INTO `cart`(`person_id`,`flower_id`,`date`,`amount`) VALUES('3','15','2018-04-04 09:48:28','3');
INSERT INTO `cart`(`person_id`,`flower_id`,`date`,`amount`) VALUES('3','42','2018-04-04 09:48:28','6');
INSERT INTO `cart`(`person_id`,`flower_id`,`date`,`amount`) VALUES('3','6','2018-04-04 09:48:28','5');
INSERT INTO `cart`(`person_id`,`flower_id`,`date`,`amount`) VALUES('3','29','2018-04-04 09:48:28','3');
INSERT INTO `cart`(`person_id`,`flower_id`,`date`,`amount`) VALUES('3','33','2018-04-04 09:48:28','6');
INSERT INTO `cart`(`person_id`,`flower_id`,`date`,`amount`) VALUES('3','37','2018-04-04 09:48:28','5');
INSERT INTO `cart`(`person_id`,`flower_id`,`date`,`amount`) VALUES('3','2','2018-04-04 09:48:28','3');
INSERT INTO `cart`(`person_id`,`flower_id`,`date`,`amount`) VALUES('3','27','2018-04-04 09:48:28','4');
INSERT INTO `cart`(`person_id`,`flower_id`,`date`,`amount`) VALUES('3','10','2018-04-04 09:48:28','3');
INSERT INTO `cart`(`person_id`,`flower_id`,`date`,`amount`) VALUES('3','44','2018-04-04 09:48:28','6');
INSERT INTO `cart`(`person_id`,`flower_id`,`date`,`amount`) VALUES('3','16','2018-04-04 09:48:28','5');
INSERT INTO `cart`(`person_id`,`flower_id`,`date`,`amount`) VALUES('3','39','2018-04-04 09:48:28','3');



-- ---------------------------------------------------------------------------------------------

-- ----------------------------
-- 配送信息表
-- ----------------------------
/*DROP TABLE IF EXISTS `delivery`;
CREATE TABLE `delivery`(
	`order_id` VARCHAR(20) NOT NULL COMMENT '订单编号',
    `person_id`  INT(25) UNSIGNED COMMENT '用户ID',
    `date` DATETIME COMMENT '配送时间',
    `send_add` VARCHAR(255) COMMENT '开始地址',
    `send_desc` VARCHAR(255) NOT NULL COMMENT '目的地',
    `note` VARCHAR(255) DEFAULT NULL COMMENT '备注',
    CONSTRAINT `delivery_person_id` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT `delivery_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
*/
-- ----------------------------
-- delivery表记录
-- ----------------------------
-- INSERT INTO `delivery` VALUES();
-- ---------------------------------------------------------------------------------------------
/*
-- ----------------------------
-- image表
-- ----------------------------
DROP TABLE IF EXISTS `images`;
CREATE TABLE `images`(
	`images_id` VARCHAR(255) COMMENT '图像id',
    `images_url` VARCHAR(1024) COMMENT '图像url',
    `images_type` TINYINT(5) COMMENT '图像类型 0:用户头像,1:鲜花图像'
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
*/

/*
ON DELETE CASCADE 父表删除时级联删除

ON UPDATE NO ACTION 父表更新时不做任何操作

*/
SET FOREIGN_KEY_CHECKS=1;

DROP TABLE IF EXISTS todo;

CREATE TABLE todo (
	id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
	title varchar(50),
	status varchar(10),
	description varchar(100),
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO todo(title, status, description) VALUES('買い物に行く','未着手', '仕事終わりに行く');
INSERT INTO todo(title, status, description) VALUES('美術館に行く','未着手', '目当ての絵画を見る');
INSERT INTO todo(title, status, description) VALUES('本を読む','進行中', '積読を消化する');
INSERT INTO todo(title, status, description) VALUES('paypyaにチャージする','完了', 'お昼までに済ませておく');
INSERT INTO todo(title, status, description) VALUES('散髪に行く','未着手', '伸びてきたので切りに行く');
INSERT INTO todo(title, status, description) VALUES('本を買う','未着手', '勉強用の本を買いに行く');
INSERT INTO todo(title, status, description) VALUES('課題を進める','進行中', '順番に取り組んでいく');
INSERT INTO todo(title, status, description) VALUES('上着を用意する','完了', 'オフィス用の上着を用意しておく');

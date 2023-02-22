# Day27

## Setup
### 1. export URl
```
export MONGO_URL="mongodb+srv://cluster0.xfgf96o.mongodb.net"
```

### 2. export Username
```
export USERNAME="tohdarryl"
```

### 3. mongoimport
```
mongoimport $MONGO_URL --username $USERNAME -d bgg -c comment --jsonArray --file ./comment.json --drop
```

## On Terminal
### 1. login as db admin
```
mysql -uroot -p
```

### 2. import the database
```
source <filename>; e.g. bgg.sql
```

### 3. verify DB has been imported
```
show databases
```

### 4. grant user access to DB
```
grant all privileges on <db>.* to '<user>'@'%';
```

### 5. flush privileges
```
flush privileges;
```

### 6. exit and test DB with user
```
exit;
mysql -u nonroot -p
```

### 7. check mongolDB on localhost
```
1. mongosh
2. use bgg;
3. db.<collection>.find({c_id:"<id>"});

```
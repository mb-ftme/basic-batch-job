# IR_SFARA_beta_backend_for_chapar

IP address is

```bash
192.168.16.171
```

password is

```bash
faRa!1401#D@1
```

user is

```bash
fara
```

service directory



pscp command to transfer

```bash
pscp -r IR_SFARA_beta_backend_for_chapar fara@192.168.16.171:/home/fara/srv
```

```
faRa!1401#D@1
```


# time problem solving
```SHELL
timedatectl list-timezones
```

![[Pasted image 20220712201746.png]]

```SHELL
sudo timedatectl set-timezone  Asia/Tehran
```


```SHELL
sudo timedatectl set-timezone  Etc/UTC
```


# command
```
 timedatectl set-time '09:55:00'
```
# error
```shell
Failed to set time: Automatic time synchronization is enabled
```
# solve
```shell
sudo timedatectl set-ntp 0
```

then use the set time command again

and use set time command

this command will also work

```shell
 date --set="14 SEP 2022 07:56:00"
```

#### but use sudo

# creating a database for the project
is the next step

getting core count for task execution
```java
int cores = Runtime.getRuntime().availableProcessors();
```

no database is configured and it just downloads the file
___
___

## for not having the Internet on the servers of the v refah I use a new way of deployment of the server

## I upload jar file and use java command to run that file for ever as a service in system d

## نکته ی بسیار مهم
### 1. سرور چاپار وثبت احوال و شاهکار اینترنت ندارد برای نصب لایبری های آن باید
### 2.  نکته ی دوم همه ی پورت های آن نیاز به دستور برای باز شدن دارند و چند پورت آن نمیتواند باز باشد
### 3. همیشه ساعت های این سرور ها اشتباه است
### 4. یک راه حل این است که تایم رو از کلاینت بگیریم


![Pasted image 20220913152538](Pasted%20image%2020220913152538.png)
creating a ref id which is
*webservice type*
`1` for send
`2` for inquiry

*identifier_id*
which is a unique number for v refah
```number
1011
```

*current_unix_time_stamp*
```timestamp
15:31:37.1575
```

but only time  numbers just connected

[there is an error in the spring boot when using h2 with foreign key](there%20is%20an%20error%20in%20the%20spring%20boot%20when%20using%20h2%20with%20foreign%20key.md)

[curl for the chapar request](curl%20for%20the%20chapar%20request.md)

![Pasted image 20220913194803](Pasted%20image%2020220913194803.png)

[changing linux server time by hand](changing%20linux%20server%20time%20by%20hand.md)
[change time zone in linux](change%20time%20zone%20in%20linux.md)

```shell
{"status_code":"125","message_fa":".تولید شده، که شامل 10 کاراکتر عددی می باشد، خارج از بازه ی زمانی مجاز-اختلاف ده دقیقه ای- می باشد RefID استفاده شده در timestamp برچسب زمانی"}
```

# server status
server ip
```ip_address
192.168.16.171
```

server user name
```uname
fara
```

server password
```pass
faRa!1401#D@1
```

file directory is

```path
/home/fara/Chapar_4558
```

service runner shell `project file directory`
```java
/home/fara/Chapar_4558/
```

service runner `shell file` includings
```shell
cd /home/fara/Chapar_4558
java -jar chapar-0.0.1-SNAPSHOT.jar
```

*hint dont use source for running
like *
```java
source java -jar
```
use
```java
java -jar
```

`shell file directory` creating
```path
/usr/bin/chapar_runner.sh
```

`permission granting for shell file`for the shell file to be able to run
```shell
 sudo chmod +x /usr/bin/chapar_runner.sh
```
*it is runnable*

running the test file once step
```shell 
sh /usr/bin/chapar_runner.sh
```
*test is ok*

### creating a service to activat the shell
*service file path will be *
```java
/lib/systemd/system/chapar.service
```

_command to craete service file_
```bash
sudo nano /lib/systemd/system/chapar.service
```


```password
faRa!1401#D@1
```

_internals of the file_
```bash
[Unit]
Description= service runner for chapar port 4558

[Service]
ExecStart=sh /usr/bin/chapar_runner.sh
Restart=always

[Install]
WantedBy=multi-user.target
```


__reloading the service now__

```bash
sudo systemctl daemon-reload
```

```shell
sudo systemctl enable chapar.service
```

```shell
sudo systemctl start chapar.service
```

```shell
sudo systemctl status chapar.service
```

```shell
sudo systemctl restart chapar.service
```

```shell
sudo systemctl stop chapar.service
```

```bash
sudo journalctl -f -u chapar
```

```password
faRa!1401#D@1
```


```bash
journalctl --since "2022-04-04 00:00:00" --until "2022-04-05 16:12:00" -f -u fwd_srv_runner
```

```password
faRa!1401#D@1
```

# source on github is
[mxm5/IR_SFARA_beta_backend_for_chapar (github.com)](https://github.com/mxm5/IR_SFARA_beta_backend_for_chapar)

opened endpoints
new update on chapar but not pushed on the server
// now the server is running
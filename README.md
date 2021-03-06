# PunkChatBot

## About

<img src="markup/botAvatar.jpg" width="300" height="300" alt="botAvatar">

Chat ~~roulette~~ bot for finding people to talk for SPbU students.

## Rules

* 📜 It's only allowed to send text messages
* 💣 You have an opportunity to ban user if he doesn't behave good
* 📤 You may enter the community only if someone will share an invitation link with you
    * You are responsible for those who you invite: if they behave badly, community can easily find who is responsible
      for invitation
* ⏲ You have only one 12 hours to speak with your interlocutor (so you'd rather don't spend time wastefully 😉)
* 🔕 It's not allowed to send any kind of links

## How to start
TODO

![Bot preview](markup/botPreview.png)

## Development

- [ ] Invitation System
- [ ] Interlocutor finding
- [ ] Message transfer
- [ ] Ban system
- [ ] Form filling keyboard
- [ ] Chat timer
- [ ] Logging system

____

## Environment

1) Ubuntu 20.04 (with **systemd** init system)
2) MongoDB Community Edition
    1) Running **mongod** service (sudo systemctl status mongod
       )
    2) Interactive work through mongosh
    3) *chatbot* db (collections: userForm)
3) `vkconfig.properties` file containing group access token and id (contact contributors to obtain the config data)

## Contribution:

If you'd like to help in developing this bot, write to one of contributors:  
* [Emir Vildanov](https://vk.com/general_bum), Telegram :  @EmirVildanov
* There may be you 😉

If you'd like to edit diagrams, write to me, and I will give you an editor rights:

* [**Link**](https://online.visual-paradigm.com/share.jsp?id=313930363330382d31) to Chat StateMachine diagram
* [**Link**](https://dbdiagram.io/d/624954fad043196e39e57e3c) to DataBase diagram  


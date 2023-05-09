/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example;

import org.example.excelManipulation.XLSXManipulation;
import org.example.humanFabrics.HumanFabric;
import org.example.players.Human;
import org.example.players.Player;

import javax.swing.*;

/**
 * Класс для описания игры
 * @author Мария
 */
public class Game {

    CharacterAction action = new CharacterAction();
    ChangeTexts change = new ChangeTexts();
    Fight fight = new Fight();
    HumanFabric humanFabric = new HumanFabric();
    XLSXManipulation xlsxManipulation = new XLSXManipulation();
    int location;


    /**
     * Создание вражеского игрока и установка начальных параметров
     * @return вражеский персонаж
     */
    public Player NewEnemy(JLabel L1, JLabel L2,
                           JLabel L3, JLabel L4, JProgressBar pr2) {
        action.setEnemyes();
        Player enemy = action.ChooseEnemy(L1, L2, L3, L4);
        pr2.setMaximum(enemy.getMaxHealth());
        action.HP(enemy, pr2);

        return enemy;
    }

    /**
     * Создание персонажа игрока
     * @param location заданное кол-во локаций в игре
     * @param name имя выбранного персонажа
     * @return персонаж игрока
     */
    public Human NewHuman(JProgressBar pr1, int location, String name){
        Human human = (Human) humanFabric.create(name,location);
        pr1.setMaximum(human.getMaxHealth());
        action.HP(human, pr1);
        return human;
    }


}

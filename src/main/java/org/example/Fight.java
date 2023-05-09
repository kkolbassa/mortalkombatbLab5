/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example;

import org.example.excelManipulation.Result;
import org.example.players.Human;
import org.example.players.Player;
import org.example.players.ShaoKahn;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Класс для описания возможных действий персонажей
 * @author Мария
 */
public class Fight {

    ChangeTexts change = new ChangeTexts();
    int kind_attack[] = {0};
    int i = 1;
    int k = -1;
    int stun = 0;
    double v = 0.0;
    int location = 1;
    int countRoundLocation = 2;

    /**
     * Описание результата от выбранных действий игрока и врага
     */
    public void Move(Player p1, Player p2, JLabel l, JLabel l2) {
        if (stun == 1) {
            p1.setAttack(-1);
        }

        switch (Integer.toString(p1.getAttack()) + Integer.toString(p2.getAttack())) {
            case "10":
                v = Math.random();
                if (p1 instanceof ShaoKahn & v < 0.15) {
                    p2.setHealth(-(int) (p1.getDamage() * 0.5));
                    l2.setText("Your block is broken");

                } else {
                    p2.setHealth(-(int) (p1.getDamage() * 0.5));
                    l2.setText(p2.getName() + " counterattacked");
                }
                break;
            case "11":
                p2.setHealth(-p1.getDamage());
                l2.setText(p1.getName() + " attacked");
                break;
            case "00":
                v = Math.random();
                if (v <= 0.5) {
                    stun = 1;
                }
                l2.setText("Both defended themselves");
                break;
            case "01":
                l2.setText(p1.getName() + " didn't attacked");
                break;
            case "-10":
                l.setText(p1.getName() + " was stunned");
                stun = 0;
                l2.setText(p2.getName() + " didn't attacked");
                break;
            case "-11":
                p1.setHealth(-p2.getDamage());
                l.setText(p1.getName() + " was stunned");
                stun = 0;
                l2.setText(p2.getName() + " attacked");
                break;
            case "20":
                p1.setHealth((int) ((p1.getMaxHealth()- p1.getHealth()) * 0.5));
                l2.setText(p1.getName() + " regenerated health");
                break;
            case "21":
                p1.setHealth(-(int) (p2.getDamage() * 2));
                l2.setText(p2.getName() + " counterattacked regen");
                break;
            case "12":
                p2.setHealth(-(int) (p1.getDamage() * 2));
                l2.setText(p1.getName() + " counterattacked regen");
                break;
            case "02":
                p2.setHealth((int) ((p2.getMaxHealth()- p2.getHealth()) * 0.5));
                l2.setText(p2.getName() + " regenerated health");
                break;
            case "-12":
                l.setText(p1.getName() + " was stunned");
                stun = 0;
                p2.setHealth((int) ((p2.getMaxHealth()- p2.getHealth()) * 0.5));
                l2.setText(p2.getName() + " regenerated health");
                break;
        }

    }

    /**
     * Описание действий при выборе игроком атаки/защиты
     * @param human игрок
     * @param enemy вражеский персонаж
     * @param a выбор игрока атаковать или защищаться
     */
    public void Hit(Player human, Player enemy, int a, JLabel label,
                    JLabel label2, JDialog dialog, JLabel label3, CharacterAction action,
                    JProgressBar pr1, JProgressBar pr2, JDialog dialog1,
                    JDialog dialog2, JFrame frame, ArrayList<Result> results,
                    JLabel label4, JLabel label5, JLabel label6, JLabel label7,
                    JLabel label8, Items[] items, JRadioButton rb, JLabel jLabelNextLocation) {
        label7.setText("");
        human.setAttack(a);
        human.addStrategy(a);

        if (k < kind_attack.length - 1) {
            k++;
        } else {
            kind_attack = action.ChooseBehavior(enemy, action, human.getStrategy(), i);
            k = 0;
        }

        if (enemy instanceof ShaoKahn&&(Math.random()<0.1)){
            enemy.setAttack(2);
            k--;
        }else enemy.setAttack(kind_attack[k]);

        if (i % 2 == 1) {
            Move(human, enemy, label7, label8);
        } else {
            Move(enemy, human, label7, label8);
        }
        i++;
        change.RoundTexts(human, enemy, label, label2, i, label6);
        action.HP(human, pr1);
        action.HP(enemy, pr2);

        if (human.getHealth() <= 0 & items[2].getCount() > 0) {
            human.setNewHealth((int) (human.getMaxHealth() * 0.05));
            items[2].setCount(-1);
            action.HP(human, pr1);
            label2.setText(human.getHealth() + "/" + human.getMaxHealth());
            rb.setText(items[2].getName() + ", " + items[2].getCount() + " шт");
            label7.setText("Вы воскресли");
        }
        if (human.getHealth() <= 0 | enemy.getHealth() <= 0) {
                EndRound(human, enemy, dialog, label3, action, items,dialog1, dialog2,
                        frame, results,  label4, label5,jLabelNextLocation);
        }
    }

    /**
     * Описание действий при завершении раунда
     * @param human игрок
     * @param enemy вражеский персонаж
     */
    public void EndRound(Player human, Player enemy, JDialog dialog, JLabel label,
            CharacterAction action, Items[] items, JDialog dialog1,
                         JDialog dialog2, JFrame frame, ArrayList<Result> results,JLabel label4, JLabel label5, JLabel jLabelNextLocation) {

        ((Human) human).setLocalRound();
        human.setNullStrategy();
        jLabelNextLocation.setText("");
        if (human.getHealth() > 0) {
            label.setText("You win");
            ((Human) human).setWin();

            if (enemy instanceof ShaoKahn) {
                action.AddItems(38, 23, 8, items);
                action.AddPointsBoss(((Human) human), action.getEnemyes());
            } else {
                action.AddItems(25, 15, 5, items);
                action.AddPoints(((Human) human), action.getEnemyes());
            }
        } else {
            label.setText(enemy.getName() + " win");
        }
        if(((Human) human).getLocalRound() == countRoundLocation){
            location++;
            setCountRoundLocation(human.getLevel());
            ((Human) human).setLocalRoundNull();
            jLabelNextLocation.setText("Вы перешли на следующую локацию");
        }
        if(location==1+((Human) human).getLocation()){
            EndFinalRound(((Human) human), action, results, dialog1, dialog2,
                    frame, label4, label5);
        }else{
            dialog.setVisible(true);
            dialog.setBounds(300, 150, 700, 600);
        }

        i = 1;
        k = -1;
        kind_attack = ResetAttack();

    }

    /**
     * Рассчитывает кол-во раундов для локации в зависимости от уровня игрока
     * @param level уровень игрока
     */
    private void setCountRoundLocation(int level) {
        this.countRoundLocation = level + 2;
    }

    /**
     * Описание действий при завершении финального раунда
     * @param human игрок
     */
    public void EndFinalRound(Human human, CharacterAction action,
            ArrayList<Result> results, JDialog dialog1, JDialog dialog2, JFrame frame,
            JLabel label1, JLabel label2) {
        String text = "Победа не на вашей стороне";
        if (human.getHealth() > 0) {
            human.setWin();
            action.AddPoints(human, action.getEnemyes());
            text = "Победа на вашей стороне";
        }
        boolean top = false;
        if (results == null) {
            top = true;
        } else {
            int i = 0;
            for (int j = 0; j < results.size(); j++) {
                if (human.getPoints() < results.get(j).getPoints()) {
                    i++;
                }
            }
            if (i < 10) {
                top = true;
            }
        }
        if (top) {
            dialog1.setVisible(true);
            dialog1.setBounds(150, 150, 600, 500);
            label1.setText(text);
        } else {
            dialog2.setVisible(true);
            dialog2.setBounds(150, 150, 470, 360);
            label2.setText(text);
        }
        frame.dispose();
    }

    public int[] ResetAttack() {
        int a[] = {0};
        return a;
    }

    /**
     * Описание действий для нового раунда
     * @param human игрок
     * @return враг для нового раунда
     */
    public Player NewRound(Player human, JLabel label, JProgressBar pr1,
            JProgressBar pr2, JLabel label2, JLabel text, JLabel label3, CharacterAction action) {

        Player enemy1 = null;
        if (((Human) human).getLocalRound() == countRoundLocation-1) {
            enemy1 = action.ChooseBoss(label, label2, text, label3);
        } else {
            enemy1 = action.ChooseEnemy(label, label2, text, label3);
        }
        pr1.setMaximum(human.getMaxHealth());
        pr2.setMaximum(enemy1.getMaxHealth());
        human.setNewHealth(human.getMaxHealth());
        enemy1.setNewHealth(enemy1.getMaxHealth());
        action.HP(human, pr1);
        action.HP(enemy1, pr2);
        return enemy1;
    }

}

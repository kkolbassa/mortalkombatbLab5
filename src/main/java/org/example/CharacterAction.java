/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example;

import org.example.enemyFabrics.EnemyFabric;
import org.example.players.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Класс для описания действий вражеского персонажа и изменения характеристик игроков
 * @author Мария
 */
public class CharacterAction {
    private final int kind_fight[][] = {{1, 0}, {1, 1, 0}, {0, 1, 0}, {1, 1, 1, 1}};

    private Player enemyes[] = new Player[5];

    EnemyFabric fabric = new EnemyFabric();

    private Player enemy = null;
    private final Random random = new Random();

    /**
     * Создание вражеских персонажей
     */
    public void setEnemyes() {
        enemyes[0] = fabric.create(0, 0);
        enemyes[1] = fabric.create(1, 0);
        enemyes[2] = fabric.create(2, 0);
        enemyes[3] = fabric.create(3, 0);
        enemyes[4] = fabric.create(4, 0);
    }

    public Player[] getEnemyes() {
        return this.enemyes;
    }

    /**
     * Выбор вражеского персонажа
     * @return вражеский песронаж
     */
    public Player ChooseEnemy(JLabel label, JLabel label2, JLabel text, JLabel label3) {
        int i = (int) (Math.random() * 4);
        ImageIcon icon1 = null;
        switch (i) {
            case 0:
                enemy = enemyes[0];
                icon1 = new ImageIcon(getClass().getClassLoader().getResource("images/Baraka.png"));
                label2.setText("Baraka (танк)");
                break;
            case 1:
                enemy = enemyes[1];
                icon1 = new ImageIcon(getClass().getClassLoader().getResource("images/SubZero.png"));
                label2.setText("Sub-Zero (маг)");
                break;
            case 2:
                enemy = enemyes[2];
                icon1 = new ImageIcon(getClass().getClassLoader().getResource("images/LiuKang.png"));
                label2.setText("Liu Kang (боец)");
                break;
            case 3:
                enemy = enemyes[3];
                icon1 = new ImageIcon(getClass().getClassLoader().getResource("images/SonyaBlade.png"));
                label2.setText("Sonya Blade (солдат)");
                break;
        }
        label.setIcon(icon1);
        text.setText(Integer.toString(enemy.getDamage()));
        label3.setText(Integer.toString(enemy.getHealth()) + "/" + Integer.toString(enemy.getMaxHealth()));
        return enemy;
    }

    /**
     * Выбор босса
     * @return вражеский персонаж
     */
    public Player ChooseBoss(JLabel label, JLabel label2, JLabel text, JLabel label3) {
        ImageIcon icon1 = null;
        icon1 = new ImageIcon(getClass().getClassLoader().getResource("images/ShaoKahn.png"));
        label2.setText("Shao Kahn (босс)");
        enemy = enemyes[4];
        label.setIcon(icon1);
        text.setText(Integer.toString(enemy.getDamage()));
        label3.setText(Integer.toString(enemy.getHealth()) + "/" + Integer.toString(enemy.getMaxHealth()));
        return enemy;
    }

    /**
     * Выбор базовой стратегии врага в зависимости от его вида
     * @return стратегия врага
     */
    public int[] EnemyBehavior(int k1, int k2, int k3, int k4, double i) {
        int arr[] = null;
        if (i < k1 * 0.01) {
            arr = kind_fight[0];
        }
        if (i >= k1 * 0.01 & i < (k1 + k2) * 0.01) {
            arr = kind_fight[1];
        }
        if (i >= (k1 + k2) * 0.01 & i < (k1 + k2 + k3) * 0.01) {
            arr = kind_fight[2];
        }
        if (i >= (k1 + k2 + k3) * 0.01 & i < 1) {
            arr = kind_fight[3];
        }
        return arr;
    }

    /**
     * Описание выбора базовой стратегии врага в зависимости от его вида
     * @param enemy
     * @param action
     */
    public int[] ChooseBasicBehavior(Player enemy, CharacterAction action) {
        int arr[] = null;
        double i = Math.random();
        if (enemy instanceof Baraka) {
            arr = action.EnemyBehavior(15, 15, 60, 10, i);
        }
        if (enemy instanceof SubZero) {
            arr = action.EnemyBehavior(25, 25, 0, 50, i);
        }
        if (enemy instanceof LiuKang) {
            arr = action.EnemyBehavior(13, 13, 10, 64, i);
        }
        if (enemy instanceof SonyaBlade) {
            arr = action.EnemyBehavior(25, 25, 50, 0, i);
        }
        if (enemy instanceof ShaoKahn) {
            arr = action.EnemyBehavior(10, 45, 0, 45, i);
        }
        return arr;
    }

    /**
     * Описание выбора стратегии врага
     * @param strategy ходы игрока
     * @param step чей ход
     */
    public int[] ChooseBehavior(Player enemy, CharacterAction action, ArrayList<Integer> strategy, int step) {
        if(step<2){
            return ChooseBasicBehavior(enemy, action);
        }else return ChooseOptimalBehavior(enemy, strategy, step);
    }

    /**
     * Выбор оптимальной стратегии врага
     * @return стратегия врага
     */
    private int[] ChooseOptimalBehavior(Player enemy, ArrayList<Integer> strategyPlayer, int step) {
        int index = 0;
        int limit = 0;
        if(enemy instanceof SubZero) limit =2;
        else if(enemy instanceof SonyaBlade) limit = 3;

        List<Integer> strategy = strategyPlayer.subList(strategyPlayer.size()-2,strategyPlayer.size());
        switch (Integer.toString(strategy.get(0)) + Integer.toString(strategy.get(1))) {
            case "00":
                if(step%2==1){
                    if(limit==2) index = random.nextInt(2) == 0 ? 1 : 3;
                    else if(limit==3) index = random.nextInt(2) == 0 ? 1 : 2;
                        else {
                            if(random.nextBoolean())index = random.nextInt(2) == 0 ? 2 : 3;
                            else index = random.nextInt(2) == 0 ? 2 : 1;
                    }
                }else if(limit==2) index = 3;
                    else if(limit==3) index = 2;
                        else index = random.nextInt(2) == 0 ? 3 : 2;
                        break;
            case "01":
                    if(limit==2) index =0;
                    else index = random.nextInt(2) == 0 ? 2 : 0;
                break;
            case "10":
                if(step%2==1){
                    if(limit==2) index = random.nextInt(2) == 0 ? 0 : 1;
                    else {
                        if(random.nextBoolean())index = random.nextInt(2) == 0 ? 2 : 0;
                        else index = random.nextInt(2) == 0 ? 2 : 1;
                    }
                }else if(limit==3) index = random.nextInt(2) == 0 ? 0 : 1;
                else {
                    if(random.nextBoolean())index = random.nextInt(2) == 0 ? 3 : 0;
                    else index = random.nextInt(2) == 0 ? 3 : 1;
                }
                break;
            case"11":
                if(step%2==1){
                    if(limit==2) index = 1;
                    else index = random.nextInt(2) == 0 ? 2 : 1;
                }else if(limit==3) index = random.nextInt(2) == 0 ? 0 : 1;
                    else {
                        if(random.nextBoolean())index = random.nextInt(2) == 0 ? 3 : 0;
                        else index = random.nextInt(2) == 0 ? 3 : 1;
                    }
                break;
        }
        return kind_fight[index];
    }

    /**
     * Установление полосы здоровья игрока в Jframe
     * @param player игрок
     */
    public void HP(Player player, JProgressBar progress) {

        if (player.getHealth() >= 0) {
            progress.setValue(player.getHealth());
        } else {
            progress.setValue(0);
        }
    }

    /**
     * Добавление очков и опыта за победу над врагом
     * @param human игрок
     * @param enemyes враги
     */
    public void AddPoints(Human human, Player[] enemyes) {
        human.setExperience(20 + human.getLevel()*5);
        human.setPoints(25 + human.getLevel()*10 + human.getHealth() / 4);
        checkLevel(human, enemyes);
    }

    /**
     *
     * Добавление очков и опыта за победу над боссом
     * @param human игрок
     * @param enemyes враги
     */
    public void AddPointsBoss(Human human, Player[] enemyes) {
        human.setExperience(30 + 20*human.getLevel());
        human.setPoints(45 +10*human.getLevel()+ human.getHealth() / 2);
        checkLevel(human, enemyes);
    }

    /**
     * Проверка перехода игрока на новый уровень
     * @param human
     * @param enemyes
     */
    public void checkLevel(Human human, Player[] enemyes){
        if (human.getExperience() >= human.getNextExperience() ) {
            human.setLevel();
            human.setNextExperience(human.getNextExperience() * (human.getLevel() + 1));

            Object[] options = {"Max Health",
                    "Damage"};
            int chose = JOptionPane.showOptionDialog(null,
                    "Какие характеристики улучшить?",
                    "Level up!",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,     //do not use a custom Icon
                    options,  //the titles of buttons
                    options[0]); //default button title

            NewHealthHuman(human, chose);
            for (int j = 0; j < enemyes.length; j++) {
                NewHealthEnemy(enemyes[j], human);
            }
        }
    }

    /**
     * Добавление предметов в сумку
     */
    public void AddItems(int k1, int k2, int k3, Items[] items) {
        double i = Math.random();
        if (i < k1 * 0.01) {
            items[0].setCount(1);
        }
        if (i >= k1 * 0.01 & i < (k1 + k2) * 0.01) {
            items[1].setCount(1);
        }
        if (i >= (k1 + k2) * 0.01 & i < (k1 + k2 + k3) * 0.01) {
            items[2].setCount(1);
        }
    }

    /**
     * Результат выбора улучшения характеристики
     * @param human игрок
     * @param chose выбранная характеристика
     */
    public void NewHealthHuman(Human human, int chose) {

        switch (chose) {
            case 0:
                human.setMaxHealth(25 + human.getLevel()*5);
                break;
            case 1:
                human.setDamage(3+human.getLevel());
        }
    }

    /**
     * Увеличение характеристик врага при переходе на новый уровень
     */
    public void NewHealthEnemy(Player enemy, Human human) {
        enemy.setMaxHealth((int) enemy.getMaxHealth() * (34 - human.getLevel()*2) / 100);
        enemy.setDamage((int) enemy.getDamage() * (26 - human.getLevel()) / 100);
        enemy.setLevel();
    }

    /**
     * Выбор какой предмет использовать
     */
    public void UseItem(Player human, Items[] items, String name, JDialog dialog, JDialog dialog1) {
        switch (name) {
            case "jRadioButton1":
                if (items[0].getCount() > 0) {
                    human.setHealth((int) (human.getMaxHealth() * 0.25));
                    items[0].setCount(-1);
                } else {
                    dialog.setVisible(true);
                    dialog.setBounds(300, 200, 400, 300);
                }
                break;
            case "jRadioButton2":
                if (items[1].getCount() > 0) {
                    human.setHealth((int) (human.getMaxHealth() * 0.5));
                    items[1].setCount(-1);
                } else {
                    dialog.setVisible(true);
                    dialog.setBounds(300, 200, 400, 300);
                }
                break;
            case "jRadioButton3":
                dialog.setVisible(true);
                dialog.setBounds(300, 200, 400, 300);
                break;
        }
        
        if(dialog.isVisible()==false){
            dialog1.dispose();
        }
    }
}

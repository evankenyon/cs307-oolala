package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import model.commands.Command;

/**
 * Purpose: This class is meant to do the work of parsing for the Logo IDE. When the user inputs
 * commands, either in the text box or in file format, this class does the work of parsing the text
 * into commands that the turtles will follow to be able to draw on the screen. Assumptions: The
 * user can input commands either into the text box or in .txt file format. This class is expected
 * to be able to handle incorrect commands, or improper file formats. Dependencies: File,
 * FileNotFoundException, IOException, PrintWriter, StandardCharsets, ArrayList,
 * InputMismatchException, List, Properties, Scanner, Command, GoHomeCommand, HideCommand,
 * MoveBackwardCommand, MoveForwardCommand, RotateLeftCommand, RotateRightCommand,
 * SetPenDownCommand, SetPenUpCommand, ShowCommand, StampCommand, TellCommand, PropertiesLoader
 * Example: Create an instance of this class in the high level model for the program. When the user
 * inputs text as commands and hits run or enter, call on this class to create the commands that
 * must be run, then have these run in the model.
 *
 * @Author Evan Kenyon
 */
public abstract class CommandModel {

  /**
   * Purpose: Get a list of commands for the turtle(s) to execute based on the commands given by the
   * user as strings of text. Assumptions: Any incorrect commands will throw exceptions.
   *
   * @param input
   * @return List of Commands to be executed by the turtles
   * @throws InputMismatchException
   * @throws IllegalArgumentException
   */
  public abstract List<Command> getCommandsFromInput(String input)
      throws InputMismatchException, IllegalArgumentException;

}

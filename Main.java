import java.util.Scanner;

class Main {
  
  public static final String PASTRY_CAKE = "Cake";
  public static final String PASTRY_PIE = "Pie";
  public static final String PASTRY_COOKIE = "Cookie";

  public static final String FLAVOR_CHOCOLATE = "Chocolate";
  public static final String FLAVOR_STRAWBERRY = "Strawberry";
  public static final String FLAVOR_VANILLA = "Vanilla";

  public static final String TEXTURE_CHEWY = "Chewy";
  public static final String TEXTURE_SOFT = "Soft";
  public static final String TEXTURE_CRISPY = "Crispy";

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    String opponentPastry = getRandomPastry();
    String opponentFlavor = getRandomFlavor();
    String opponentTexture = getRandomTexture();
    int opponentQuality = getRandomQuality();

    System.out.print("Enter your pastry name (Cake, Pie, Cookie): ");
    String pastry = scanner.next();
    
    System.out.print("Enter your pastry flavor (Chocolate, Strawberry, Vanilla): ");
    String flavor = scanner.next();
    
    System.out.print("Enter your pastry texture (Chewy, Crispy, Soft): ");
    String texture = scanner.next();
    
    System.out.print("Enter your pastry quality (1-10): ");
    int quality = Integer.parseInt(scanner.next());

    int opponentScore = scorePastry(opponentPastry, opponentFlavor, opponentTexture, opponentQuality);
    int playerScore = scorePastry(pastry, flavor, texture, quality);

    if (playerScore > opponentScore) {
      System.out.println("Player wins with a score of " + playerScore + " against the opponent's score of " + opponentScore + "!");
      printBestPastry(pastry, flavor, texture);
    } else {
      System.out.println("Opponent wins with a score of " + opponentScore + " against the player's score of " + playerScore + "!");
      printBestPastry(opponentPastry, opponentFlavor, opponentTexture);
    }
  }

  public static String getRandomPastry() {
    Double chance = Math.random();
    if (chance <= .33) {
      return PASTRY_CAKE;
    } else if (chance <= .67) {
      return PASTRY_COOKIE;
    } else {
      return PASTRY_PIE;
    }
  }

  public static String getRandomFlavor() {
    Double chance = Math.random();
    if (chance <= .33) {
      return FLAVOR_CHOCOLATE;
    } else if (chance <= .67) {
      return FLAVOR_STRAWBERRY;
    } else {
      return FLAVOR_VANILLA;
    }
  }

  public static String getRandomTexture() {
    Double chance = Math.random();
    if (chance <= .33) {
      return TEXTURE_CHEWY;
    } else if (chance <= .67) {
      return TEXTURE_CRISPY;
    } else {
      return TEXTURE_SOFT;
    }
  }

  public static Integer getRandomQuality() {
    return (int) (Math.random() * 9 + 1);
  }

  public static Integer scorePastry(String pastry, String flavor, String texture, int quality) {
    
    int score = quality;

    score += scorePastry(pastry);
    score += scoreFlavor(flavor);
    score += scoreTexture(texture);

    if (pastry == PASTRY_PIE) {
      score += scorePieSpecial(flavor, texture);
    } else if (pastry == PASTRY_CAKE) {
      score += scoreCakeSpecial(flavor, texture);
    } else if (pastry == PASTRY_COOKIE) {
      score += scoreCookieSpecial(flavor, texture);
    }

    score *= quality * .1 + 1;

    return score;
  }

  public static int scorePastry(String pastry) {

    int score = 0;

    if (pastry == PASTRY_CAKE) {
      score += 3;
    } else if (pastry == PASTRY_PIE) {
      score += 5;
    } else if (pastry == PASTRY_COOKIE) {
      score += 2;
    }

    return score;
  }

  public static int scoreFlavor(String flavor) {

    int score = 0;

    if (flavor == FLAVOR_CHOCOLATE) {
      score += 5;
    } else if (flavor == FLAVOR_STRAWBERRY) {
      score += 1;
    } else if (flavor == FLAVOR_VANILLA) {
      score += 3;
    }

    return score;
  }

  public static int scoreTexture(String texture) {

    int score = 0;

    if (texture == TEXTURE_CHEWY) {
      score += 5;
    } else if (texture == TEXTURE_SOFT) {
      score += 1;
    } else if (texture == TEXTURE_CRISPY) {
      score += 3;
    }

    return score;
  }

  public static int scorePieSpecial(String flavor, String texture) {

    int score = 0;

    if (flavor == FLAVOR_STRAWBERRY) {
      score += 3;
    }

    if (texture == TEXTURE_CHEWY) {
      score -= 3;
    }

    return score;
  }

  public static int scoreCakeSpecial(String flavor, String texture) {

    int score = 0;

    if (flavor == FLAVOR_CHOCOLATE) {
      score += 2;
    }

    if (texture == TEXTURE_SOFT) {
      score += 3;
    }

    return score;
  }

  public static int scoreCookieSpecial(String flavor, String texture) {

    int score = 0;

    if (flavor == FLAVOR_STRAWBERRY) {
      score -= 1;
    } else if (flavor == FLAVOR_CHOCOLATE) {
      score += 2;
    }

    if (texture == TEXTURE_CHEWY) {
      score += 5;
    }

    return score;
  }

  public static void printBestPastry(String pastry, String flavor, String texture) {
      System.out.println("The best pastry was the " + texture + " " + flavor + " " + pastry + "!");
  }
}
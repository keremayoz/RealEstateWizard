package realestatewizard;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

/**
 * @author Kerem
 *
 */
public class Wizard
{

    /**
     * Default constructor
     */
    public Wizard()
    {
    }

    /**
     * @param type
     * @param re
     * @return
     */
    public ArrayList<RealEstate> estateType(String type, ArrayList<RealEstate> re)
    {
        ArrayList<RealEstate> newRE = new ArrayList<RealEstate>();
        for (int i = 0; i < re.size(); i++)
        {
            if (type.equals(re.get(i).getType()))
            {
                newRE.add(re.get(i));
            }
        }
        return newRE;
    }

    /**
     * Checks
     *
     * @param type
     * @param re
     * @return
     */
    public ArrayList<RealEstate> type(String type, ArrayList<RealEstate> re)
    {
        ArrayList<RealEstate> newRE = new ArrayList<RealEstate>();
        for (int i = 0; i < re.size(); i++)
        {
            for (int j = 0; j < re.get(i).cl.size(); j++)
            {
                if (type.equals(re.get(i).cl.get(j).getType()))
                {
                    newRE.add(re.get(i));
                }
            }
        }
        return newRE;
    }

    /**
     * Edits the given ArrayList<RealEstate> by price descending
     *
     * @param prices
     * @param lowerIndex
     * @param upperIndex
     */
    public static void cheapToExpensive(ArrayList<Integer> prices, int lowerIndex, int upperIndex)
    {
        int i = lowerIndex;
        int j = upperIndex;
        int h; // temporary variables
        int key = prices.get((int) (Math.floor((i + j) / 2)));
        do
        {
            while (prices.get(i) < key && i < prices.size())
            {
                i++;
            }
            while (prices.get(j) > key && j > 0)
            {
                j--;
            }
            if (i <= j)
            {
                h = prices.get(i);
                prices.set(i, prices.get(j));
                prices.set(j, h);
                i++;
                j--;
            }
        } while (i <= j);
        if (lowerIndex < j)
        {
            cheapToExpensive(prices, lowerIndex, j);
        }
        if (upperIndex > i)
        {
            cheapToExpensive(prices, i, upperIndex);
        }
    }

    /**
     * Edits the given ArrayList<RealEstate> by price ascending
     *
     * @param prices
     * @param lowerIndex
     * @param upperIndex
     */
    public static void expensiveToCheap(ArrayList<Integer> prices, int lowerIndex, int upperIndex)
    {
        int i = lowerIndex;
        int j = upperIndex;
        int h; // temporary variables
        int key = prices.get((int) (Math.floor((i + j) / 2)));
        do
        {
            while (prices.get(i) > key && i < prices.size())
            {
                i++;
            }
            while (prices.get(j) < key && j > 0)
            {
                j--;
            }
            if (i <= j)
            {
                h = prices.get(i);
                prices.set(i, prices.get(j));
                prices.set(j, h);
                i++;
                j--;
            }
        } while (i <= j);
        if (lowerIndex < j)
        {
            expensiveToCheap(prices, lowerIndex, j);
        }
        if (upperIndex > i)
        {
            expensiveToCheap(prices, i, upperIndex);
        }
    }

    /**
     * Returns the estates which have 'key' in their name,description and
     * address. Priority is following: Name-Address-Description
     *
     * @param re
     * @param key
     * @return
     */
    public ArrayList<RealEstate> search(ArrayList<RealEstate> re, String key)
    {
        ArrayList<RealEstate> keyInName = new ArrayList<RealEstate>();
        ArrayList<RealEstate> keyInDescription = new ArrayList<RealEstate>();
        ArrayList<RealEstate> keyInLocation = new ArrayList<RealEstate>();
        Scanner scan;
        key = editString(key);
        Scanner keyScan = new Scanner(key);
        while (keyScan.hasNext())
        {
            String word = keyScan.next();
            for (int i = 0; i < re.size(); i++)
            {
                boolean added = false;
                String name = re.get(i).title;
                String description = re.get(i).description;
                String location = re.get(i).address;

                scan = new Scanner(editString(name));
                while (scan.hasNext() && !added)
                {
                    if (word.equals(scan.next()))
                    {
                        keyInName.add(re.get(i));
                        added = true;
                    } // if
                }
                scan = new Scanner(editString(description));
                while (scan.hasNext() && !added)
                {
                    if (word.equals(scan.next()))
                    {
                        keyInDescription.add(re.get(i));
                        added = true;
                    } // if
                }
                scan = new Scanner(editString(location));
                while (scan.hasNext() && !added)
                {
                    if (word.equals(scan.next()))
                    {
                        keyInLocation.add(re.get(i));
                        added = true;
                    } // if
                }
            } // for
        }
        for (int i = 0; i < keyInLocation.size(); i++)
        {
            keyInName.add(keyInLocation.get(i));
        }

        for (int i = 0; i < keyInDescription.size(); i++)
        {
            keyInName.add(keyInDescription.get(i));
        }
        return keyInName;
    }

    /**
     * Edits the String, removes the punctuation
     *
     * @param input
     * @return
     */
    public String editString(String input)
    {
        String output = input.replace(".", "");
        output = output.replace(",", "");
        output = output.replace("\"", "");
        output = output.replace(":", "");
        output = output.replace(";", "");
        return output.toLowerCase();
    }

    /**
     * Reduction by sale price
     *
     * @param re
     * @param min
     * @param max
     * @return
     */
    public ArrayList<RealEstate> priceSale(ArrayList<RealEstate> re, int min, int max)
    {
        ArrayList<RealEstate> newRE = new ArrayList<RealEstate>();
        for (int i = 0; i < re.size(); i++)
        {
            if (re.get(i).getSalePrice() >= min && re.get(i).getSalePrice() <= max)
            {
                newRE.add(re.get(i));
            }
        }
        return newRE;
    }

    /**
     * Reduction by long term loan price
     *
     * @param re
     * @param min
     * @param max
     * @return
     */
    public ArrayList<RealEstate> priceLong(ArrayList<RealEstate> re, int min, int max)
    {
        ArrayList<RealEstate> newRE = new ArrayList<RealEstate>();
        for (int i = 0; i < re.size(); i++)
        {
            if (re.get(i).getLongPrice() >= min && re.get(i).getLongPrice() <= max)
            {
                newRE.add(re.get(i));
            }
        }
        return newRE;
    }

    /**
     * Reduction by short term loan price
     *
     * @param re
     * @param min
     * @param max
     * @return
     */
    public ArrayList<RealEstate> priceShort(ArrayList<RealEstate> re, int min, int max)
    {
        ArrayList<RealEstate> newRE = new ArrayList<RealEstate>();
        for (int i = 0; i < re.size(); i++)
        {
            if (re.get(i).getShortPrice() >= min && re.get(i).getShortPrice() <= max)
            {
                newRE.add(re.get(i));
            }
        }
        return newRE;
    }

    /**
     * Returns the houses whose age is in given span
     *
     * @param re
     * @param min
     * @param max
     * @return
     */
    public ArrayList<RealEstate> age(ArrayList<RealEstate> re, int min, int max)
    {
        ArrayList<RealEstate> newRE = new ArrayList<RealEstate>();
        for (int i = 0; i < re.size(); i++)
        {
            if (re.get(i).getType().equals("House"))
            {
                if (((House) (re.get(i))).age >= min && ((House) (re.get(i))).age <= max)
                {
                    newRE.add(re.get(i));
                }
            } else if (re.get(i).getType().equals("Office"))
            {
                if (((Office) (re.get(i))).age >= min && ((Office) (re.get(i))).age <= max)
                {
                    newRE.add(re.get(i));
                }
            }
        }
        return newRE;
    }

    /**
     * Returns the houses whose roomNumber is in given span
     *
     * @param re
     * @param min
     * @param max
     * @return
     */
    public ArrayList<RealEstate> roomNumber(ArrayList<RealEstate> re, int min, int max)
    {
        ArrayList<RealEstate> newRE = new ArrayList<RealEstate>();
        for (int i = 0; i < re.size(); i++)
        {
            if (re.get(i).getType().equals("House"))
            {
                if (((House) (re.get(i))).roomNumber >= min && ((House) (re.get(i))).roomNumber <= max)
                {
                    newRE.add(re.get(i));
                }
            } else if (re.get(i).getType().equals("Office"))
            {
                if (((Office) (re.get(i))).roomNumber >= min && ((Office) (re.get(i))).roomNumber <= max)
                {
                    newRE.add(re.get(i));
                }
            }
        }
        return newRE;
    }

    /**
     * Returns the houses whose heating is equals given type
     *
     * @param type
     * @param re
     * @return
     */
    public ArrayList<RealEstate> heating(int type, ArrayList<RealEstate> re)
    {
        ArrayList<RealEstate> newRE = new ArrayList<RealEstate>();
        for (int i = 0; i < re.size(); i++)
        {
            if (type == ((House) re.get(i)).heating)
            {
                newRE.add(re.get(i));
            }
        }
        return newRE;
    }

    /*
	 * Boolean methods to reduct estates with given booleans
     */
    /**
     * Checks the elevator presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> hasElevator(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).hasElevator)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the security presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> hasSecurity(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).hasSecurity)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the car park presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> hasCarPark(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).hasCarPark)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the indoor garage presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> hasIndoorGarage(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).hasIndoorGarage)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * @param real
     * @return
     */
    public ArrayList<RealEstate> isFurnished(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).isFurnished)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * @param real
     * @return
     */
    public ArrayList<RealEstate> isUsed(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).isUsed)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the water booster presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> waterBooster(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).waterBooster)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the termal isolation presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> thermalIsolation(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).thermalIsolation)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the sound isolation presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> soundIsolation(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).soundIsolation)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the garden presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> hasGarden(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).hasGarden)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    // *****************INTERIOR FEATURES*****************
    /**
     * Checks the painting presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> isPainted(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).isPainted)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the laminate presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> isLaminated(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).isLaminated)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the air conditioner presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> hasAirConditioner(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).hasAirConditioner)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the alarm presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> hasAlarmHouse(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).hasAlarm)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the terrace presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> hasTerrace(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).hasTerrace)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the windowBlind presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> hasWindowBlind(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).hasWindowBlind)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the parents bathroom presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> hasParentsBathroom(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).hasPArentsBathroom)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the smart home presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> isSmartHome(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).isSmartHome)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the steeldoor presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> hasSteelDoor(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).hasSteelDoor)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the water heater presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> hasWaterHeater(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).hasWaterHeater)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    // ****************GOODS********************
    /**
     * Checks the movie system presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> hasMovieSystem(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).hasMovieSystem)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the tv presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> hasTV(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).hasTV)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the oven presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> hasOven(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).hasOven)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * @param real
     * @return
     */
    public ArrayList<RealEstate> hasDishWasher(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).hasDishWasher)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the wireless presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> hasWireless(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).hasWireless)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the barbeque presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> hasBarbeque(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).hasBarbeque)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the table presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> hasTable(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).hasTable)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the gaming tools presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> hasGamingTools(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).hasGamingTools)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the kitchen tools presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> hasKitchenTools(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).hasKitchenTools)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the sauna presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> hasSauna(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).hasSauna)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the garden tools presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> hasGardenTools(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).hasGardenTools)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    // ******************NEARLINES*******************
    /**
     * Checks the mosque presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> mosque(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).mosque)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the school presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> school(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).school)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the pharmacy presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> pharmacy(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).pharmacy)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the supermarket presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> supermarket(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).supermarket)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the hospital presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> hospital(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).hospital)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the subway presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> subway(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).subway)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the bus stop presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> busStop(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("House"))
            {
                if (((House) (real.get(i))).busStop)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    // OFFICE
    /**
     * Checks the avaibility for automative
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> availableForAutomative(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("Office"))
            {
                if (((Office) (real.get(i))).availableForAutomative)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the avaibility for cafe presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> availableForCafe(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("Office"))
            {
                if (((Office) (real.get(i))).availableForCafe)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the avaibility for bakery
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> availableForBakery(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("Office"))
            {
                if (((Office) (real.get(i))).availableForBakery)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the avaibility for grocery
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> availableForGrocery(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("Office"))
            {
                if (((Office) (real.get(i))).availableForGrocery)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the avaibility for restaurant
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> availableForRestaurant(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("Office"))
            {
                if (((Office) (real.get(i))).availableForRestaurant)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the avaibility for workshop
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> availableForWorkShop(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("Office"))
            {
                if (((Office) (real.get(i))).availableForWorkShop)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the avaibility for policilinic
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> availableForPoliclinic(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("Office"))
            {
                if (((Office) (real.get(i))).availableForPoliclinic)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the avaibility for butcher
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> availableForButcher(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("Office"))
            {
                if (((Office) (real.get(i))).availableForButcher)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the avaibility for pharmacy
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> availableForPharmacy(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("Office"))
            {
                if (((Office) (real.get(i))).availableForPharmacy)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the camera presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> hasCamera(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("Office"))
            {
                if (((Office) (real.get(i))).hasCamera)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the alarm presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> hasAlarmOffice(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("Office"))
            {
                if (((Office) (real.get(i))).hasAlarm)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the generator presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> hasGenerator(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("Office"))
            {
                if (((Office) (real.get(i))).hasGenerator)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the kitchen presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> hasKitchen(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("Office"))
            {
                if (((Office) (real.get(i))).hasKitchen)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the wc presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> hasWC(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("Office"))
            {
                if (((Office) (real.get(i))).hasWC)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    // LAND
    /**
     * Checks the electric presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> hasElectric(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("Land"))
            {
                if (((Land) (real.get(i))).hasElectric)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the water presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> hasWater(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("Land"))
            {
                if (((Land) (real.get(i))).hasWater)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the natural gas system presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> hasNaturalGas(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("Land"))
            {
                if (((Land) (real.get(i))).hasNaturalGas)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the open path presence
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> isPathOpened(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("Land"))
            {
                if (((Land) (real.get(i))).isPathOpened)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the nearliness of main road
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> isCloseToMainRoad(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("Land"))
            {
                if (((Land) (real.get(i))).isCloseToMainRoad)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Checks the nearliness of transportation
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> isCloseToTransportation(ArrayList<RealEstate> real)
    {
        ArrayList<RealEstate> output = new ArrayList<RealEstate>();
        for (int i = 0; i < real.size(); i++)
        {
            if (real.get(i).getType().equals("Land"))
            {
                if (((Land) (real.get(i))).isCloseToTransportation)
                {
                    output.add(real.get(i));
                }
            }
        }
        return output;
    }

    /**
     * Method for event-focused search, birthday For birthday, house should
     * contain following: movie system, wireless, gaming tools, kitchen Also it
     * should be close to a supermarket, and it should be furnished
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> birthdayEFS(ArrayList<RealEstate> real)
    {
        return hasMovieSystem(hasTV(hasWireless(hasGamingTools(supermarket(isFurnished(real))))));
    }

    /**
     * Method for event-focused search, barbeque For barbeque, house should
     * contain following: oven,barbeque,garden,terrace,supermarket,kitchen tools
     * Also it should be furnished
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> barbequeEFS(ArrayList<RealEstate> real)
    {
        return hasOven(hasBarbeque(hasGarden(hasTerrace( supermarket(isFurnished(hasKitchenTools(real)))))));
    }

    /**
     * Method for event-focused search, barbeque For barbeque, house should
     * contain following: oven,barbeque,garden,terrace,supermarket,kitchen tools
     * Also it should be furnished
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> businessEFS(ArrayList<RealEstate> real)
    {
        return hasSecurity(hasWireless(hasTV(isFurnished(hasTable((real))))));
    }

    /**
     * Method for event-focused search, party For party, house should contain
     * following: terrace,gaming tools,movie
     * system,supermarket,subway,kitchen,garden Also it should be furnished
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> partyEFS(ArrayList<RealEstate> real)
    {
        return hasGamingTools(hasTerrace(hasMovieSystem(supermarket(subway(hasGarden((real)))))));
    }

    /**
     * Method for event-focused search, wedding For wedding, house should
     * contain following: car park,terrace,garden, table Also it should be
     * furnished
     *
     * @param real
     * @return
     */
    public ArrayList<RealEstate> weddingEFS(ArrayList<RealEstate> real)
    {
        return hasCarPark(hasTerrace(hasGarden(isFurnished(hasTable((real))))));
    }

    /**
     * @param re
     * @return
     */
    public ArrayList<RealEstate> estatesForSale(ArrayList<RealEstate> re)
    {
        ArrayList<RealEstate> newRE = new ArrayList<RealEstate>();
        for (int i = 0; i < re.size(); i++)
        {
            if (re.get(i).getSalePrice() > 0)
            {
                newRE.add(re.get(i));
            }
        }
        return newRE;
    }

    /**
     * @param re
     * @return
     */
    public ArrayList<RealEstate> estatesForLong(ArrayList<RealEstate> re)
    {
        ArrayList<RealEstate> newRE = new ArrayList<RealEstate>();
        for (int i = 0; i < re.size(); i++)
        {
            if (re.get(i).getLongPrice() > 0)
            {
                newRE.add(re.get(i));
            }
        }
        return newRE;
    }

    /**
     * @param type
     * @param re
     * @return
     */
    public ArrayList<RealEstate> estatesForShort(ArrayList<RealEstate> re)
    {
        ArrayList<RealEstate> newRE = new ArrayList<RealEstate>();
        for (int i = 0; i < re.size(); i++)
        {
            if (re.get(i).getShortPrice() > 0)
            {
                newRE.add(re.get(i));
            }
        }
        return newRE;
    }
    
    	//Parametre olarak Date arraylisti ald�m ama daha sonra realestate arraylisti al�p
	//metotlar�n i�erisinde tarihe g�re d�zenlemesini yapmak zor olmaz.

    /**
     *
     * @param re
     * @param lowerIndex
     * @param upperIndex
     */

	public void newerToOlder(ArrayList<RealEstate> re, int lowerIndex, int upperIndex){
		int i = lowerIndex;
		int j = upperIndex;
		ArrayList<Date> dates = new ArrayList<Date>();
		for(int m = 0; m < re.size(); m++)
			dates.add(re.get(m).getCurrentDay());
		RealEstate h; // temporary variables
		Date key = dates.get((int)(Math.floor((i+j)/2)));
		do {
			while(dates.get(i).after(key) && i < dates.size())
				i++;
			while(dates.get(j).before(key) && j > 0)
				j--;
			if (i <= j){
				h = re.get(i);
				re.set(i,re.get(j));
				re.set(j,h);
				i++; j--;
			}
		}while(i <= j);
		if (lowerIndex < j)
			newerToOlder(re, lowerIndex, j);
		if (upperIndex > i)
			newerToOlder(re, i, upperIndex);
	}

    /**
     * Sorts  estates by date with quicksort algorithm
     * We take algorithm from http://www.baskent.edu.tr/~tkaracay/etudio/ders/prg/dataStructures/sorting/QuickSort/QuickSort.pdf
     *
     * @param re
     * @param lowerIndex
     * @param upperIndex
     */
    public void olderToNewer(ArrayList<RealEstate> re, int lowerIndex, int upperIndex){
		int i = lowerIndex;
		int j = upperIndex;
		ArrayList<Date> dates = new ArrayList<Date>();
		for(int m = 0; m < re.size(); m++)
			dates.add(re.get(m).getCurrentDay());
		RealEstate h; // temporary variables
		Date key = dates.get((int)(Math.floor((i+j)/2)));
		do {
			while(dates.get(i).before(key) && i < dates.size())
				i++;
			while(dates.get(j).after(key) && j > 0)
				j--;
			if (i <= j){
				h = re.get(i);
				re.set(i,re.get(j));
				re.set(j,h);
				i++; j--;
			}
		}while(i <= j);
		if (lowerIndex < j)
			olderToNewer(re, lowerIndex, j);
		if (upperIndex > i)
			olderToNewer(re, i, upperIndex);
	}
        
    /**
     *
     * @param re
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public BufferedImage map(RealEstate re) throws MalformedURLException, IOException{
		String adress = re.address;
		Scanner scan = new Scanner(adress);
		String city = scan.next();
		String town = scan.next();
		String street = scan.next();
		String no = scan.next();
		String loc = "https://maps.googleapis.com/maps/api/staticmap?center=" + street + "," + town + "," + city + "&zoom=13&size=200x200&maptype=roadmap&markers=color:red%7C" + street + "," + town + "," + city + "&AIzaSyBUNY81ygcgUR0hb50xFHGOZxXCsJu2sXg";
		URL url = new URL(loc);
		BufferedImage image = ImageIO.read(url);
		return image;
    }
}


package org.dziadzi.services.impl;

import org.dziadzi.converters.LocationToDtoConverter;
import org.dziadzi.dtos.LocationDto;
import org.dziadzi.nodes.Location;
import org.dziadzi.repositories.LocationRepository;
import org.dziadzi.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;
import static org.dziadzi.nodes.builders.LocationBuilder.aLocation;

/**
 * Created by kkuc on 2016-03-17.
 */
@RestController("locations")
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private LocationToDtoConverter converter;

	@Override
	public List<LocationDto> getLocationsRow(Integer y) {

		List<Location> row = locationRepository.findByY(y);
		return row.stream().map(converter).collect(Collectors.toList());

	}

	@Override
	public void createLocations(int maxX, int maxY) {
		for (int y = 0; y < maxY; y++) {
			for (int x = 0; x < maxX; x++) {
				Location toSave = aLocation().withX(x).withY(y)
						.build();
				addNeighbours(toSave);
				locationRepository.save(toSave, 1);
			}

		}
	}

	@Override
	public Location getLocationEntity(int x, int y) {
		return locationRepository.findByXAndY(x, y);
	}

	@Override
	public Location findOne(Long aLong) {
		return locationRepository.findOne(aLong);
	}

	private void addNeighbours(Location toSave) {
		Location left = locationRepository.findByXAndY(toSave.getX() - 1,
				toSave.getY());
		//Location upperLeft = locationRepository
		//		.findByXAndY(toSave.getX() - 1, toSave.getY() - 1);
		Location upper = locationRepository.findByXAndY(toSave.getX(),
				toSave.getY() - 1);
		//Location upperRight = locationRepository
		//		.findByXAndY(toSave.getX() + 1, toSave.getY() - 1);
		List<Location> potentialNeighbours = newArrayList(left,
				//upperLeft, upperRight,
				upper);
		potentialNeighbours.stream().filter(e -> e != null)
				.forEach(e -> toSave.getNeighbours().add(e));

	}

	@Override
	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteLocations() {
		locationRepository.deleteAll();
	}

	@Override
	@RequestMapping(value = "count", method = RequestMethod.GET)
	public Long count() {
		return locationRepository.count();
	}

	@Override
	public List<Location> getLocations(List<Integer> longitues, List<Integer> lattitudes) {
		List<Location> toReturn = new ArrayList<>();
		for (int i = 0; i < longitues.size(); i++) {
			Location byXAndY = locationRepository
					.findByXAndY(longitues.get(i), lattitudes.get(i));
			toReturn.add(byXAndY);
		}
		return toReturn;
	}

}

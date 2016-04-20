package org.dziadzi.converters;

import org.dziadzi.dtos.ForkLiftDto;
import org.dziadzi.dtos.builders.ForkLiftDtoBuilder;
import org.dziadzi.nodes.ForkLift;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * Created by DELL on 2016-04-20.
 */
@Component
public class ForkLiftToDtoConverter implements Function<ForkLift, ForkLiftDto> {

	@Override
	public ForkLiftDto apply(ForkLift forkLift) {
		if (forkLift == null) {
			return null;
		}
		return ForkLiftDtoBuilder.aForkLiftDto().withDirection(forkLift.getDirection()).build();
	}
}
